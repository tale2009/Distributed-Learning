package tccdemo.server;

import io.seata.rm.tcc.api.BusinessActionContext;
import io.seata.rm.tcc.api.BusinessActionContextParameter;
import io.seata.rm.tcc.api.LocalTCC;
import io.seata.rm.tcc.api.TwoPhaseBusinessAction;

import java.math.BigDecimal;

@LocalTCC
public interface OrderServer {
    @TwoPhaseBusinessAction(name = "orderService", commitMethod = "orderConfirm", rollbackMethod = "orderCancel")
    boolean orderTry(BusinessActionContext actionContext,
                     @BusinessActionContextParameter(paramName = "ordId") Long ordId);

    boolean orderConfirm(BusinessActionContext actionContext);

    boolean orderCancel(BusinessActionContext actionContext);
}