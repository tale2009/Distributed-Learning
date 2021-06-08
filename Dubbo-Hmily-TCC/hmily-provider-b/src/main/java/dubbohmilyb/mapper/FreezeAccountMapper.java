package dubbohmilyb.mapper;

import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;

public interface FreezeAccountMapper {
    /**
     * 冷结美金
     * @param money
     * @param id
     * @return
     */
    public Integer freezeAccountUS(@Param("money")BigDecimal money,@Param("id")Long id);
    /**
     * 解冷美金
     * @param money
     * @param id
     * @return
     */
    public Integer unFreezeAccountUS(@Param("money")BigDecimal money,@Param("id")Long id);
}
