package dubbohmily;

public interface AccountDubboApiA {
    public Boolean CNYtoUStry(TransferPO transferPO);
    public Boolean CNYtoUSCommit(TransferPO transferPO);
    public Boolean CNYtoUSCanCel(TransferPO transferPO);

}
