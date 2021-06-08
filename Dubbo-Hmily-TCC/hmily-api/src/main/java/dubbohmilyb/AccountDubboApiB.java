package dubbohmilyb;

import dubbohmily.TransferPO;

public interface AccountDubboApiB {
    public Boolean UStoCNYtry(TransferPO transferPO);
    public Boolean UStoCNYCommit(TransferPO transferPO);
    public Boolean UStoCNYCanCel(TransferPO transferPO);
}
