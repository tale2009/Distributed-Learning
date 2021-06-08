package dubbohmily.mapper;

import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;

public interface FreezeAccountMapper {
    /**
     * 冷结人民币
     * @param money
     * @param id
     * @return
     */
    public Integer freezeAccountCNY(@Param("money") BigDecimal money, @Param("id")Long id);
    /**
     * 解冷人民币
     * @param money
     * @param id
     * @return
     */
    public Integer unFreezeAccountCNY(@Param("money") BigDecimal money, @Param("id")Long id);
}
