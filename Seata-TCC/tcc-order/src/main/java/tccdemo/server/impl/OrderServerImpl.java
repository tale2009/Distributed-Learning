package tccdemo.server.impl;

import io.seata.rm.tcc.api.BusinessActionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tccdemo.OrderPO;
import tccdemo.mapper.OrderMapper;
import tccdemo.server.OrderServer;

import java.math.BigDecimal;
import java.util.Date;

@Service
public class OrderServerImpl implements OrderServer {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private OrderMapper orderMapper;

    @Override
    public boolean orderTry(BusinessActionContext actionContext, Long ordId) {
        String xId = actionContext.getXid();
        long branchId = actionContext.getBranchId();
        logger.info("当前：Xid：【{}】,BranchId：【{}】",xId,branchId);
        Date currentDate=new Date();
        OrderPO orderPO=new OrderPO();
        orderPO.setOrderId(ordId);
        orderPO.setNotes("");
        orderPO.setTotalPrice(BigDecimal.ZERO);
        orderPO.setStatus("");
        orderPO.setPaymentTime(currentDate);
        orderPO.setShipTime(currentDate);
        orderPO.setDealTime(currentDate);
        orderPO.setCreateTime(currentDate);
        orderPO.setModifyTime(currentDate);
        orderPO.setModifyUserId(1L);
        orderPO.setCreateUserId(1L);
        Integer insert = orderMapper.insert(orderPO);
        if(insert==0){
            throw new RuntimeException("生成订单 Try 阶段失败.");
        }
        logger.info("当前：Xid：【{}】,BranchId：【{}】,生成订单号码：【{}】",xId,branchId,ordId);
        return true;
    }

    @Override
    public boolean orderConfirm(BusinessActionContext actionContext) {
        return true;
    }

    @Override
    public boolean orderCancel(BusinessActionContext actionContext) {
        String xId = actionContext.getXid();
        long branchId = actionContext.getBranchId();
        Long orderId = ((Long) actionContext.getActionContext("ordId"));
        logger.info("当前：Xid：【{}】,BranchId：【{}】,准备要删除：【{}】数据",xId,branchId,orderId);
        orderMapper.deleteByOrderId(orderId);
        return true;
    }
}
