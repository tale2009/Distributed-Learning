package tccdemo.controller;

import io.seata.rm.tcc.api.BusinessActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tccdemo.server.StockServer;

import java.math.BigDecimal;

@RestController
@RequestMapping("/stock")
public class StockController {
    @Autowired
    private StockServer stockServer;

    @RequestMapping(value = "/try",method = RequestMethod.POST)
    public boolean stockTry(@RequestBody BusinessActionContext actionContext,
                            @RequestParam("stockId") Long stockId,
                            @RequestParam("saled") BigDecimal saled){
        return stockServer.stockTry(actionContext, stockId, saled);

    }
    @PostMapping("/confirm")
    public boolean stockConfirm(@RequestBody BusinessActionContext actionContext){
        return stockServer.stockConfirm(actionContext);
    }

    @PostMapping("/cancel")
    public boolean stockCancel(@RequestBody BusinessActionContext actionContext){
        return stockServer.stockCancel(actionContext);
    }
}
