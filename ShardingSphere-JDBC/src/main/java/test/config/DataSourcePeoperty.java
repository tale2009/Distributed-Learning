package test.config;
import com.zaxxer.hikari.HikariConfig;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * 数据源相关配置
 */
@Component
@ConfigurationProperties("sharding")
public class DataSourcePeoperty {
    private Map<String, HikariConfig> datasources;

    public Map<String, HikariConfig> getDatasources() {
        return datasources;
    }

    public void setDatasources(Map<String, HikariConfig> datasources) {
        this.datasources = datasources;
    }
}
