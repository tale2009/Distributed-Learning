package dubbohmily.service.impl;

import dubbohmily.AccountDubboApiA;
import dubbohmilyb.AccountDubboApiB;
import dubbohmily.TransferPO;
import dubbohmily.service.ConsumerService;
import org.apache.dubbo.config.annotation.Reference;
import org.dromara.hmily.annotation.HmilyTCC;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class ConsumerImpl implements ConsumerService {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Reference
    private AccountDubboApiA accountDubboApiA;
    @Reference
    private AccountDubboApiB accountDubboApiB;

    @HmilyTCC(confirmMethod = "consumerCommit", cancelMethod = "consumerCancel")
    @Override
    public Boolean consumerTry(TransferPO transferPO) {
        logger.info("=========开始转汇=========");
        accountDubboApiA.CNYtoUStry(transferPO);
        System.out.println(1/0);
        return true;
    }

    @Override
    public Boolean consumerCommit(TransferPO transferPO) {
        logger.info("=========转汇成功=========");
        return true;
    }

    @Override
    public Boolean consumerCancel(TransferPO transferPO) {
        logger.info("=========总体转汇回滚=========");
//        accountDubboApiA.CNYtoUSCanCel(transferPO);
//        accountDubboApiB.UStoCNYCanCel(transferPO);
        return true;
    }
}
