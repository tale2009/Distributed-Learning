package tccdemo.service;

import tccdemo.DealPO;

import java.net.UnknownHostException;

public interface BusinessService {
    public void buy(DealPO dealPO) throws UnknownHostException;
}