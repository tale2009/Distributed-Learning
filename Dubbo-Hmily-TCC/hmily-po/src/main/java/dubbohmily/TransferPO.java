package dubbohmily;

import java.io.Serializable;
import java.math.BigDecimal;

public class TransferPO implements Serializable {
    private Long cnWalletid;
    private Long usWalletid;
    private BigDecimal CNYMoney;
    private BigDecimal USMoney;

    public BigDecimal getCNYMoney() {
        return CNYMoney;
    }

    public void setCNYMoney(BigDecimal CNYMoney) {
        this.CNYMoney = CNYMoney;
    }

    public BigDecimal getUSMoney() {
        return USMoney;
    }

    public void setUSMoney(BigDecimal USMoney) {
        this.USMoney = USMoney;
    }

    public Long getCnWalletid() {
        return cnWalletid;
    }

    public void setCnWalletid(Long cnWalletid) {
        this.cnWalletid = cnWalletid;
    }

    public Long getUsWalletid() {
        return usWalletid;
    }

    public void setUsWalletid(Long usWalletid) {
        this.usWalletid = usWalletid;
    }
}

