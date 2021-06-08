package dubbohmily.controller;

import dubbohmily.TransferPO;
import dubbohmily.service.ConsumerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
public class ConsumerController {
    @Autowired
    private ConsumerService consumerService;
    @RequestMapping(value = "exchange",method = RequestMethod.POST)
    public void exchange(@RequestBody TransferPO transferPO)
    {
        transferPO.setUSMoney(BigDecimal.valueOf(1));
        transferPO.setCNYMoney(BigDecimal.valueOf(7));
        consumerService.consumerTry(transferPO);
    }
}
