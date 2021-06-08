package dubbohmilyb.impl;

import dubbohmilyb.AccountDubboApiB;
import dubbohmily.TransferPO;
import dubbohmilyb.mapper.AccountMapper;
import dubbohmilyb.mapper.FreezeAccountMapper;
import org.apache.dubbo.config.annotation.Service;
import org.dromara.hmily.annotation.HmilyTCC;
import org.dromara.hmily.common.exception.HmilyException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;

@Service
public class AccountDubboApiBImpl implements AccountDubboApiB {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private AccountMapper accountMapper;
    @Autowired
    private FreezeAccountMapper freezeAccountMapper;
    @HmilyTCC(confirmMethod = "UStoCNYCommit", cancelMethod = "UStoCNYCanCel")
    @Override
    public Boolean UStoCNYtry(TransferPO transferPO) {
        logger.info("尝试冷结资产");
        //兑换美金
        Integer result=accountMapper.subtractAccountUS(transferPO.getUSMoney(),transferPO.getUsWalletid());
        result=freezeAccountMapper.freezeAccountUS(transferPO.getUSMoney(),transferPO.getUsWalletid());
        if(result!=1)
            throw new HmilyException("资产冷结失败");
        logger.info("冷结资产{}:，兑换美金:{}",transferPO.getUsWalletid(),transferPO.getUSMoney());

        return true;
    }

    @Override
    public Boolean UStoCNYCommit(TransferPO transferPO) {
        logger.info("执行兑换{}->{}",transferPO.getUsWalletid(),transferPO.getUSMoney().multiply(BigDecimal.valueOf(7L)));
        accountMapper.exchangeAccountCNY(transferPO.getUSMoney().multiply(BigDecimal.valueOf(7L)),transferPO.getUsWalletid());
        freezeAccountMapper.unFreezeAccountUS(transferPO.getUSMoney(),transferPO.getUsWalletid());
        return true;
    }

    @Override
    public Boolean UStoCNYCanCel(TransferPO transferPO) {
        logger.info("兑换回滚");
        if(transferPO.getUSMoney()!=null)
        {
            accountMapper.returnAccountUS(transferPO.getUSMoney(),transferPO.getUsWalletid());
            freezeAccountMapper.unFreezeAccountUS(transferPO.getUSMoney(),transferPO.getUsWalletid());
        }
        return true;
    }
}
