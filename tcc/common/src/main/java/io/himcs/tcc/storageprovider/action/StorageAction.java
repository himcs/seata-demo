package io.himcs.tcc.storageprovider.action;

import io.seata.rm.tcc.api.BusinessActionContext;
import io.seata.rm.tcc.api.BusinessActionContextParameter;
import io.seata.rm.tcc.api.TwoPhaseBusinessAction;

public interface StorageAction {
    // Seata TCC RM 端 核心注解 ，用于声明TCC对应方法
    @TwoPhaseBusinessAction(name = "TccStorageAction",commitMethod = "commit", rollbackMethod = "rollback")
    boolean prepare(BusinessActionContext actionContext,
                    @BusinessActionContextParameter(paramName = "goodsCode") String goodsCode,
                    @BusinessActionContextParameter(paramName = "quantity") int quantity
    );

    boolean commit(BusinessActionContext actionContext);

    boolean rollback(BusinessActionContext actionContext);
}
