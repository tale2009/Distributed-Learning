package tccdemo.controller;

import io.seata.rm.tcc.api.BusinessActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import tccdemo.server.OrderServer;

import java.math.BigDecimal;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderServer orderServer;

    @PostMapping("/try")
    public boolean orderTry(@RequestBody BusinessActionContext actionContext,
                            @RequestParam("ordId") Long ordId){
        return orderServer.orderTry(actionContext, ordId);

    }
    @PostMapping("/confirm")
    public boolean orderConfirm(@RequestBody BusinessActionContext actionContext){
        return orderServer.orderConfirm(actionContext);
    }

    @PostMapping("/cancel")
    public boolean orderCancel(@RequestBody BusinessActionContext actionContext){
        return orderServer.orderCancel(actionContext);
    }
}
