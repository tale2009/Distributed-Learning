package tccdemo.fegin;

import io.seata.rm.tcc.api.BusinessActionContext;
import io.seata.rm.tcc.api.BusinessActionContextParameter;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;

@FeignClient("TCC-STOCK-SERVER")
@RequestMapping("/stock")
public interface StockFegin {
    @PostMapping("/try")
    public boolean stockTry(@RequestBody BusinessActionContext actionContext,
                            @RequestParam("stockId") Long stockId,
                            @RequestParam("saled") BigDecimal saled);
    @PostMapping("/confirm")
    boolean stockConfirm(@RequestBody BusinessActionContext actionContext);
    @PostMapping("/cancel")
    boolean stockCancel(@RequestBody BusinessActionContext actionContext);
}