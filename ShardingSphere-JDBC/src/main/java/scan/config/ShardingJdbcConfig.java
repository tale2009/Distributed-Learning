package scan.config;

import com.zaxxer.hikari.HikariDataSource;
import org.apache.shardingsphere.api.config.masterslave.MasterSlaveRuleConfiguration;
import org.apache.shardingsphere.core.yaml.swapper.impl.ShardingRuleConfigurationYamlSwapper;
import org.apache.shardingsphere.shardingjdbc.api.MasterSlaveDataSourceFactory;
import org.apache.shardingsphere.shardingjdbc.api.ShardingDataSourceFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
@Import(value ={DataSourcePeoperty.class,RuleProperties.class})
@Configuration
public class ShardingJdbcConfig {
    @Autowired
    private DataSourcePeoperty dataSourcePeoperty;
    @Autowired
    private RuleProperties ruleProperties;

    @Bean
    @Primary
    public DataSource getDataSource() throws SQLException {
        return buildDataSource();
    }


    private DataSource buildDataSource() throws SQLException {
        Map<String, DataSource> dataSourceMap = createDataSourceMap();
        DataSource dataSource = ShardingDataSourceFactory.createDataSource(dataSourceMap,
                new ShardingRuleConfigurationYamlSwapper().swap(ruleProperties) ,ruleProperties.getProps());
        return dataSource;
    }

    private Map<String, DataSource> createDataSourceMap()
    {
        //创建数据源集合
        Map<String, DataSource> dataSourceMap = new HashMap<>();
        dataSourcePeoperty.getDatasources().entrySet().forEach(e->{
            dataSourceMap.put(e.getKey(),new HikariDataSource(e.getValue()));
        });
        return dataSourceMap;
    }
}
