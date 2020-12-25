package io.himcs.tcc.orderprovider.action.impl;

import io.himcs.tcc.orderprovider.action.OrderAction;
import io.himcs.tcc.orderprovider.entity.TccOrder;
import io.himcs.tcc.orderprovider.repository.TccOrderRepository;
import io.seata.rm.tcc.api.BusinessActionContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Objects;

@Slf4j
@Service("orderActionImpl")
public class OrderActionImpl implements OrderAction {
    @Resource
    private TccOrderRepository orderRepository;

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @Override
    public boolean prepare(BusinessActionContext actionContext, String orderCode, String goodsCode, int quantity, int amount) {
        TccOrder order = TccOrder.builder()
                .orderCode(orderCode)
                .goodsCode(goodsCode)
                .quantity(quantity)
                .amount(0)
                .frozenAmount(amount)
                .status(0)
                .build();
        orderRepository.save(order);
        log.info("orderAction 分支事务已就绪, xid:{}", actionContext.getXid());
        return true;
    }

    /**
     * 冻结金额 =》 实际金额
     *
     * @return
     */
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @Override
    public boolean commit(BusinessActionContext actionContext) {
        String orderCode = (String) actionContext.getActionContext("orderCode");
        TccOrder tccOrder = orderRepository.findByOrderCode(orderCode);
        if (Objects.isNull(tccOrder)) {
            return true;
        }
        // 根据状态实现幂等性
        if (tccOrder.getStatus() == 1) {
            return true;
        }
        tccOrder.setAmount(tccOrder.getFrozenAmount());
        // 实现程序的幂等性 一次执行与多次执行 结果相同
        tccOrder.setFrozenAmount(0);
        tccOrder.setStatus(1);
        orderRepository.save(tccOrder);
        log.info("orderAction 分支事务已提交, xid:{}", actionContext.getXid());
        return true;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @Override
    public boolean rollback(BusinessActionContext actionContext) {
        String orderCode = (String) actionContext.getActionContext("orderCode");
        TccOrder tccOrder = orderRepository.findByOrderCode(orderCode);
        if (Objects.isNull(tccOrder)) {
            return true;
        }
        // 幂等性判断
        if (tccOrder.getStatus() == 2) {
            return true;
        }
        tccOrder.setAmount(0);
        tccOrder.setFrozenAmount(0);
        tccOrder.setStatus(2);
        orderRepository.save(tccOrder);
        log.info("orderAction 分支事务已回滚, xid:{}", actionContext.getXid());
        return true;
    }
}
