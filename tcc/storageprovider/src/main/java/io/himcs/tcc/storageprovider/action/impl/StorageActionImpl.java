package io.himcs.tcc.storageprovider.action.impl;

import io.himcs.tcc.storageprovider.action.StorageAction;
import io.himcs.tcc.storageprovider.entity.TccStorage;
import io.himcs.tcc.storageprovider.repository.TccStorageRepository;
import io.seata.rm.tcc.api.BusinessActionContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Objects;

@Slf4j
@Service("storageActionImpl")
public class StorageActionImpl implements StorageAction {
    @Resource
    private TccStorageRepository storageRepository;

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @Override
    public boolean prepare(BusinessActionContext actionContext, String goodsCode, int quantity) {
        TccStorage byGoodsCode = storageRepository.findByGoodsCode(goodsCode);
        if (Objects.isNull(byGoodsCode)) {
            throw new RuntimeException(goodsCode + "商品编码不存在");
        }
        if (quantity > byGoodsCode.getQuantity()) {
            throw new RuntimeException(goodsCode + "商品库存不足");
        }
        byGoodsCode.setFrozenQuantity(quantity);
        storageRepository.save(byGoodsCode);
        log.info("StorageAction 分支事务已就绪, xid:{}", actionContext.getXid());
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
        String goodsCode = (String) actionContext.getActionContext("goodsCode");
        TccStorage byGoodsCode = storageRepository.findByGoodsCode(goodsCode);
        // 具有幂等性
        byGoodsCode.setQuantity(byGoodsCode.getQuantity() - byGoodsCode.getFrozenQuantity());
        byGoodsCode.setFrozenQuantity(0);
        storageRepository.save(byGoodsCode);
        log.info("StorageAction 分支事务已提交, xid:{}", actionContext.getXid());
        return true;
    }

    // commit 或 rollback 抛出异常 TCC会一直尝试
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @Override
    public boolean rollback(BusinessActionContext actionContext) {
        String goodsCode = (String) actionContext.getActionContext("goodsCode");
        TccStorage byGoodsCode = storageRepository.findByGoodsCode(goodsCode);
        if (Objects.isNull(byGoodsCode)) {
            log.info("byGoodsCode is null");
            return true;
        }
        // 置为0 具有幂等性
        byGoodsCode.setFrozenQuantity(0);
        storageRepository.save(byGoodsCode);
        log.info("StorageAction 分支事务已回滚, xid:{}", actionContext.getXid());
        return true;
    }
}
