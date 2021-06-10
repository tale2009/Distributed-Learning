package tccdemo.server.impl;

import io.seata.rm.tcc.api.BusinessActionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tccdemo.mapper.StockMapper;
import tccdemo.server.StockServer;

import java.math.BigDecimal;

@Service
public class StockServerImpl implements StockServer {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private StockMapper stockMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean stockTry(BusinessActionContext actionContext, Long stockId, BigDecimal saled) {
        String xId = actionContext.getXid();
        long branchId = actionContext.getBranchId();
        logger.info("当前：Xid：【{}】,BranchId：【{}】",xId,branchId);
        Integer integer = stockMapper.checkStock(stockId, saled);
        if(integer==0){
            throw new RuntimeException("库存服务 Try 阶段失败.");
        }
        return true;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean stockConfirm(BusinessActionContext actionContext) {
        String xId = actionContext.getXid();
        long branchId = actionContext.getBranchId();
        Long stockId = Long.valueOf(actionContext.getActionContext("stockId").toString());
        BigDecimal saled = BigDecimal.valueOf(Long.valueOf(actionContext.getActionContext("saled").toString()));
        logger.info("当前：Xid：【{}】,BranchId：【{}】",xId,branchId);
        Integer integer = stockMapper.costStock(stockId, saled);
        if(integer==0){
            throw new RuntimeException("库存服务 Confirm 阶段失败.");
        }
        return true;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean stockCancel(BusinessActionContext actionContext) {
        Long stockId = Long.valueOf(actionContext.getActionContext("stockId").toString());
        BigDecimal saled = BigDecimal.valueOf(Long.valueOf(actionContext.getActionContext("saled").toString()));
        stockMapper.returnStock(stockId, saled);
        return true;
    }
}
