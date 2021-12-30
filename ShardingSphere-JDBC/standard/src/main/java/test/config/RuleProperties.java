package test.config;

import org.apache.shardingsphere.encrypt.api.config.rule.EncryptColumnRuleConfiguration;
import org.apache.shardingsphere.encrypt.api.config.rule.EncryptTableRuleConfiguration;
import org.apache.shardingsphere.sharding.yaml.config.YamlShardingRuleConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Properties;

@Component
@ConfigurationProperties(prefix = "rules")
public class RuleProperties  {
    @NestedConfigurationProperty
    private YamlShardingRuleConfiguration sharding = new YamlShardingRuleConfiguration();
    private Properties props = new Properties();

    public YamlShardingRuleConfiguration getSharding() {
        return sharding;
    }

    public Properties getProps() {
        return props;
    }
}

