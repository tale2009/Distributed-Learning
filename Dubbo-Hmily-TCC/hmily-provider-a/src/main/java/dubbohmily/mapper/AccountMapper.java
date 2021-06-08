package dubbohmily.mapper;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

@Repository
public interface AccountMapper {
    /**
     * 减去账户人民币
     * @param money
     * @param id
     * @return
     */
    public Integer subtractAccountCNY(@Param("money")BigDecimal money,@Param("id")Long id);


    /**
     * 兑换美金
     * @param money
     * @param id
     * @return
     */
    public Integer exchangeAccountUS(@Param("money")BigDecimal money,@Param("id")Long id);
    /**
     * 退回人民币
     * @param money
     * @param id
     * @return
     */
    public Integer returnAccountCNY(@Param("money")BigDecimal money,@Param("id")Long id);
}
