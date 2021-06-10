package tccdemo.fegin;

import io.seata.rm.tcc.api.BusinessActionContext;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;

@FeignClient("TCC-ORDER-SERVER")
@RequestMapping("/order")
public interface OrderFegin {
    @PostMapping("/try")
    public boolean orderTry(@RequestBody BusinessActionContext actionContext,
                            @RequestParam("ordId") Long ordId);

    @PostMapping("/confirm")
    public boolean orderConfirm(@RequestBody BusinessActionContext actionContext);

    @PostMapping("/cancel")
    public boolean orderCancel(@RequestBody BusinessActionContext actionContext);
}