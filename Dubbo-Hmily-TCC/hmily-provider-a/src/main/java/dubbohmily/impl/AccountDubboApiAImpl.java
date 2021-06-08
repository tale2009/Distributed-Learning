package dubbohmily.impl;

import dubbohmily.AccountDubboApiA;
import dubbohmily.TransferPO;
import dubbohmily.mapper.AccountMapper;
import dubbohmily.mapper.FreezeAccountMapper;
import dubbohmilyb.AccountDubboApiB;
import org.apache.dubbo.config.annotation.Reference;
import org.apache.dubbo.config.annotation.Service;
import org.dromara.hmily.annotation.HmilyTCC;
import org.dromara.hmily.common.exception.HmilyException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;

@Service
public class AccountDubboApiAImpl implements AccountDubboApiA {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private AccountMapper accountMapper;
    @Autowired
    private FreezeAccountMapper freezeAccountMapper;
    @Reference
    private AccountDubboApiB accountDubboApiB;
    @HmilyTCC(confirmMethod = "CNYtoUSCommit", cancelMethod = "CNYtoUSCanCel")
    @Override
    public Boolean CNYtoUStry(TransferPO transferPO) {
        logger.info("尝试冷结资产");
        //兑换人民币
        Integer updateAResult=accountMapper.subtractAccountCNY(transferPO.getCNYMoney(),transferPO.getCnWalletid());
        updateAResult=freezeAccountMapper.freezeAccountCNY(transferPO.getCNYMoney(),transferPO.getCnWalletid());
        if(updateAResult!=1)
            throw new HmilyException("A资产冷结失败");
        logger.info("冷结资产:{}，兑换人民币:{}",transferPO.getCnWalletid(),transferPO.getCNYMoney());
        logger.info("执行B用户资产操作");
        Boolean BBoolean = accountDubboApiB.UStoCNYtry(transferPO);
        if(!BBoolean)
        {
            throw new HmilyException("B资产冷结失败");

        }

        return true;
    }

    @Override
    public Boolean CNYtoUSCommit(TransferPO transferPO) {
        logger.info("执行兑换{}->{}",transferPO.getCnWalletid(),transferPO.getCNYMoney().divide(BigDecimal.valueOf(7)));
        accountMapper.exchangeAccountUS(transferPO.getCNYMoney().divide(BigDecimal.valueOf(7)),transferPO.getCnWalletid());
        freezeAccountMapper.unFreezeAccountCNY(transferPO.getCNYMoney(),transferPO.getCnWalletid());
        return true;
    }

    @Override
    public Boolean CNYtoUSCanCel(TransferPO transferPO) {
        logger.info("A兑换回滚");
        if(transferPO.getCNYMoney()!=null)
        {
            accountMapper.returnAccountCNY(transferPO.getCNYMoney(),transferPO.getCnWalletid());
            freezeAccountMapper.unFreezeAccountCNY(transferPO.getCNYMoney(),transferPO.getCnWalletid());
        }
        return true;
    }

}
