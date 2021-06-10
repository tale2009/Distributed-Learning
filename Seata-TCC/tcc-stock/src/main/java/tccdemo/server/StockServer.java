package tccdemo.server;

import io.seata.rm.tcc.api.BusinessActionContext;
import io.seata.rm.tcc.api.BusinessActionContextParameter;
import io.seata.rm.tcc.api.LocalTCC;
import io.seata.rm.tcc.api.TwoPhaseBusinessAction;

import java.math.BigDecimal;

@LocalTCC
public interface StockServer {
    @TwoPhaseBusinessAction(name = "stockService", commitMethod = "stockConfirm", rollbackMethod = "stockCancel")
    boolean stockTry(BusinessActionContext actionContext,
                     @BusinessActionContextParameter(paramName = "stockId") Long stockId,
                     @BusinessActionContextParameter(paramName = "saled") BigDecimal saled);

    boolean stockConfirm(BusinessActionContext actionContext);

    boolean stockCancel(BusinessActionContext actionContext);
}