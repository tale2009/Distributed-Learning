package tccdemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tccdemo.DealPO;
import tccdemo.service.BusinessService;

import java.net.UnknownHostException;

@RestController
@RequestMapping("/tcc")
public class BusinessController {
    @Autowired
    private BusinessService businessService;
    @RequestMapping("/buy")
    public void buy(@RequestBody DealPO dealPO) throws UnknownHostException {
        businessService.buy(dealPO);
    }
}