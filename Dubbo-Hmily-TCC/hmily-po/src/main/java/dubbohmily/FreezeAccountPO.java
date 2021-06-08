package dubbohmily;

import java.io.Serializable;
import java.math.BigDecimal;

public class FreezeAccountPO implements Serializable {
    private Long id;
    private BigDecimal cnFreeze;
    private BigDecimal usFreeze;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getCnFreeze() {
        return cnFreeze;
    }

    public void setCnFreeze(BigDecimal cnFreeze) {
        this.cnFreeze = cnFreeze;
    }

    public BigDecimal getUsFreeze() {
        return usFreeze;
    }

    public void setUsFreeze(BigDecimal usFreeze) {
        this.usFreeze = usFreeze;
    }
}
