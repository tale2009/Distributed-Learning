package dubbohmily;

import java.io.Serializable;
import java.math.BigDecimal;

public class AccountPO implements Serializable {
    private Long cnWalletid;
    private Long usWalletid;
    private String name;
    private BigDecimal cnWallet;
    public BigDecimal usWallet;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getCnWallet() {
        return cnWallet;
    }

    public void setCnWallet(BigDecimal cnWallet) {
        this.cnWallet = cnWallet;
    }

    public BigDecimal getUsWallet() {
        return usWallet;
    }

    public void setUsWallet(BigDecimal usWallet) {
        this.usWallet = usWallet;
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
