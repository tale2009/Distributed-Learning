package com.kenho.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RefreshScope
public class ValueController {
    @Value("${spring.config.version}")
    private String version;

    @RequestMapping(value = "version")
    public String getversion()
    {
        return version;
    }
}
