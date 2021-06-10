package tccdemo.service.impl;


import io.seata.core.context.RootContext;
import io.seata.rm.tcc.api.BusinessActionContext;
import io.seata.spring.annotation.GlobalTransactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tccdemo.DealPO;
import tccdemo.fegin.OrderFegin;
import tccdemo.fegin.StockFegin;
import tccdemo.service.BusinessService;
import tccdemo.IDWorker;

import java.net.UnknownHostException;

@Service
public class BusinessServiceImpl implements BusinessService {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private OrderFegin orderFegin;
    @Autowired
    private StockFegin stockFegin;

    @Override
    @GlobalTransactional
    public void buy(DealPO dealPO) throws UnknownHostException {
        String xid = RootContext.getXID();
        logger.info("事务开启：XID：【"+xid+"】");
        BusinessActionContext actionContext = new BusinessActionContext();
        actionContext.setXid(xid);
        boolean stockIsEnable = stockFegin.stockTry(actionContext, dealPO.getStockId(), dealPO.getSaled());
        if(!stockIsEnable)
        {
            logger.info("库存检查失败：XID：【"+xid+"】");
            throw new RuntimeException("扣减库存一阶段失败");
        }
        long orderId = IDWorker.getInstance("eshop_order").nextId();
        boolean createOrder=orderFegin.orderTry(actionContext, orderId);
        if(!createOrder)
        {
            logger.info("表单生成失败：XID：【"+xid+"】");
            throw new RuntimeException("表单生成一阶段失败");
        }
        System.out.println(1/0);
        return;
    }
}