package tccdemo;

import java.io.Serializable;
import java.math.BigDecimal;

public class DealPO implements Serializable {
    private Long stockId;
    private BigDecimal saled;

    public Long getStockId() {
        return stockId;
    }

    public void setStockId(Long stockId) {
        this.stockId = stockId;
    }

    public BigDecimal getSaled() {
        return saled;
    }

    public void setSaled(BigDecimal saled) {
        this.saled = saled;
    }
}
