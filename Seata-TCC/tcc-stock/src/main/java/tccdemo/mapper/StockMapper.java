package tccdemo.mapper;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;


@Repository
public interface StockMapper {
    /**
     * 检查库存
     * @param stockId
     * @param saled
     * @return
     */
    public Integer checkStock(@Param("stockId")Long stockId,@Param("saled")BigDecimal saled);

    /**
     * 进行商品库存扣费
     * @param stockId
     * @param saled
     * @return
     */
    public Integer costStock(@Param("stockId")Long stockId,@Param("saled")BigDecimal saled);

    /**
     * 进行商品库存还原
     * @param stockId
     * @param saled
     * @return
     */
    public Integer returnStock(@Param("stockId")Long stockId,@Param("saled")BigDecimal saled);
}