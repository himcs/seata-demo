package io.himcs.tcc.storageprovider.service;

import io.himcs.tcc.orderprovider.action.OrderAction;
import io.himcs.tcc.storageprovider.action.StorageAction;
import io.seata.rm.tcc.api.BusinessActionContext;
import io.seata.spring.annotation.GlobalTransactional;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class BusinessService {
    @Resource(name = "storageAction")
    private StorageAction storageAction;

    @Resource(name = "orderAction")
    private OrderAction orderAction;

    @GlobalTransactional //开启全局 TCC 分布式事务
    public void sale(String orderCode, String goodsCode, int quantity, int amount) {
        boolean result = orderAction.prepare(new BusinessActionContext(), orderCode, goodsCode, quantity, amount);
        if (!result) {
            throw new RuntimeException("orderAction exception");
        }
        result = storageAction.prepare(new BusinessActionContext(), goodsCode, quantity);
        if (!result) {
            throw new RuntimeException("storageAction exception");
        }
        if (quantity == 100) {
            throw new RuntimeException("unkonw exception");
        }
    }
}
