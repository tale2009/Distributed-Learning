package tccdemo.mapper;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import tccdemo.OrderPO;

@Repository
public interface OrderMapper {
    /**
     * 新增订单
     * @param orderPO
     * @return
     */
    public Integer insert(OrderPO orderPO);

    /**
     * 根据表单id删除订单
     * @param orderId
     */
    public void deleteByOrderId(@Param("orderId")Long orderId);
}