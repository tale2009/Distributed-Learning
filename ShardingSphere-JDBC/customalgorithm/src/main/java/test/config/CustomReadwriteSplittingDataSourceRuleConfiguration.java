package test.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@ConfigurationProperties(prefix = "rules.readwrite")
public class CustomReadwriteSplittingDataSourceRuleConfiguration {
    private  String name;

    private  String autoAwareDataSourceName;

    private  String writeDataSourceName;

    private  List<String> readDataSourceNames;

    private  String loadBalancerName;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAutoAwareDataSourceName() {
        return autoAwareDataSourceName;
    }

    public void setAutoAwareDataSourceName(String autoAwareDataSourceName) {
        this.autoAwareDataSourceName = autoAwareDataSourceName;
    }

    public String getWriteDataSourceName() {
        return writeDataSourceName;
    }

    public void setWriteDataSourceName(String writeDataSourceName) {
        this.writeDataSourceName = writeDataSourceName;
    }

    public List<String> getReadDataSourceNames() {
        return readDataSourceNames;
    }

    public void setReadDataSourceNames(List<String> readDataSourceNames) {
        this.readDataSourceNames = readDataSourceNames;
    }

    public String getLoadBalancerName() {
        return loadBalancerName;
    }

    public void setLoadBalancerName(String loadBalancerName) {
        this.loadBalancerName = loadBalancerName;
    }
}