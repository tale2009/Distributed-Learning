package dubbohmilyb;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableDubbo
@MapperScan("dubbohmilyb.mapper")
public class ProviderApplicationB {
    public static void main(String args[])
    {
        SpringApplication.run(ProviderApplicationB.class,args);
    }
}
