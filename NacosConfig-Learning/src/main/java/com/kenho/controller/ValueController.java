package com.kenho.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RefreshScope
public class ValueController {
    @Value("${nacos.test.one}")
    private String one;

//    @Value("${nacos.test.two}")
//    private String two;

    @Value("${nacos.test.other}")
    private String other;

    @RequestMapping("one")
    public void one()
    {
        System.out.println("nacos.test.one:"+one);
    }

//    @RequestMapping("two")
//    public void two()
//    {
//        System.out.println("nacos.test.two:"+two);
//    }

    @RequestMapping("other")
    public void two()
    {
        System.out.println("nacos.test.other:"+other);
    }
}
