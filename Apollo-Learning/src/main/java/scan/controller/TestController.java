package scan.controller;

import org.jasypt.encryption.StringEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @Value("${apollo.test.name}")
    private String name;

    @Value("${apollo.test.encryptname}")
    private String encryptname;

    @Value("${time}")
    private int time;

    @Value("${server.port}")
    private int port;

    @Autowired
    private StringEncryptor encryptor;


    @RequestMapping("name")
    public String name()
    {
        return name;
    }

    @RequestMapping("gray")
    public void gray()
    {
        System.out.println("这是灰度发布中端口:"+port+"服务的time值:"+time);
    }

    @RequestMapping("encrypt")
    public String encrypt()
    {
        return encryptname;
    }
}
