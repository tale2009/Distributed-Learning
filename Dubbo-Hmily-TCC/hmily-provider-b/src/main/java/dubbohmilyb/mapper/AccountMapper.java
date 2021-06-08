package dubbohmilyb.mapper;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

@Repository
public interface AccountMapper {
    /**
     * 减去账户美金
     * @param money
     * @param id
     * @return
     */
    public Integer subtractAccountUS(@Param("money")BigDecimal money,@Param("id")Long id);

    /**
     * 兑换人民币
     * @param money
     * @param id
     * @return
     */
    public Integer exchangeAccountCNY(@Param("money")BigDecimal money,@Param("id")Long id);

    /**
     * 退回美金
     * @param money
     * @param id
     * @return
     */
    public Integer returnAccountUS(@Param("money")BigDecimal money,@Param("id")Long id);
}
