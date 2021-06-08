package dubbohmily.service;

import dubbohmily.TransferPO;

public interface ConsumerService {
    public Boolean consumerTry(TransferPO transferPO);
    public Boolean consumerCommit(TransferPO transferPO);
    public Boolean consumerCancel(TransferPO transferPO);
}
