package io.himcs.tcc.orderprovider.action;

import io.seata.rm.tcc.api.BusinessActionContext;
import io.seata.rm.tcc.api.BusinessActionContextParameter;
import io.seata.rm.tcc.api.TwoPhaseBusinessAction;

public interface OrderAction {
    // Seata TCC RM 端 核心注解 ，用于声明TCC对应方法
    @TwoPhaseBusinessAction(name = "TccOrderAction",commitMethod = "commit", rollbackMethod = "rollback")
    boolean prepare(BusinessActionContext actionContext,
                   @BusinessActionContextParameter(paramName = "orderCode") String orderCode,
                    @BusinessActionContextParameter(paramName = "goodsCode") String goodsCode,
                    @BusinessActionContextParameter(paramName = "quantity")  int quantity,
                    @BusinessActionContextParameter(paramName = "amount") int amount
    );

    boolean commit(BusinessActionContext actionContext);

    boolean rollback(BusinessActionContext actionContext);
}
