package test.loadbalance;

import org.apache.shardingsphere.readwritesplitting.spi.ReplicaLoadBalanceAlgorithm;

import java.util.List;
import java.util.Properties;
import java.util.concurrent.ThreadLocalRandom;

public class CustomLoadBalanceAlgorithm implements ReplicaLoadBalanceAlgorithm {
    private Properties props = new Properties();

    @Override
    public String getDataSource(String s, String s1, List<String> list) {
        ThreadLocalRandom.current().nextInt(10);
        if(ThreadLocalRandom.current().nextInt(10)>=8)
            return "salve0-1";
        else
            return "salve0";
    }

    @Override
    public String getType() {
        return "CUSTOM";
    }

    @Override
    public Properties getProps() {
        return props;
    }

    @Override
    public void setProps(Properties props) {
        this.props = props;
    }

    @Override
    public boolean isDefault() {
        return true;
    }
}
