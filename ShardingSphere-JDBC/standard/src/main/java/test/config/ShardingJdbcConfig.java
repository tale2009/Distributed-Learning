package test.config;

import com.zaxxer.hikari.HikariDataSource;
import org.apache.shardingsphere.driver.api.ShardingSphereDataSourceFactory;
import org.apache.shardingsphere.encrypt.api.config.EncryptRuleConfiguration;
import org.apache.shardingsphere.encrypt.api.config.rule.EncryptColumnRuleConfiguration;
import org.apache.shardingsphere.encrypt.api.config.rule.EncryptTableRuleConfiguration;
import org.apache.shardingsphere.infra.config.RuleConfiguration;
import org.apache.shardingsphere.infra.config.algorithm.ShardingSphereAlgorithmConfiguration;
import org.apache.shardingsphere.readwritesplitting.api.ReadwriteSplittingRuleConfiguration;
import org.apache.shardingsphere.readwritesplitting.api.rule.ReadwriteSplittingDataSourceRuleConfiguration;
import org.apache.shardingsphere.sharding.api.config.ShardingRuleConfiguration;
import org.apache.shardingsphere.sharding.yaml.swapper.ShardingRuleConfigurationYamlSwapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.*;

@Import(value ={DataSourcePeoperty.class,
        RuleProperties.class})
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

    @ConditionalOnBean
    private DataSource buildDataSource() throws SQLException {
        List<RuleConfiguration> ruleConfigurationList=new ArrayList<>();
        ShardingRuleConfiguration shardingRuleConfiguration = new ShardingRuleConfigurationYamlSwapper().swapToObject(ruleProperties.getSharding());
        ruleConfigurationList.add(shardingRuleConfiguration);
        Map<String, DataSource> dataSourceMap = createDataSourceMap();
        DataSource dataSource = ShardingSphereDataSourceFactory.createDataSource(dataSourceMap,
                ruleConfigurationList,
                ruleProperties.getProps());
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

