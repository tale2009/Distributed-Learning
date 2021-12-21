package test.po;

import java.util.Date;

public class AdressPO {
    private Long addressId;
    private Long userId;
    private String addressName;
    private String countryCode;
    private String phone;
    private String provinceCode;
    private Long cityCode;
    private Long regionCode;
    private String detail;
    private Long createUserId;
    private Date createTime;
    private Long modifyUserId;
    private Date modifyTime;
    private String addressNameEncrypt;
    private String addressNameChiper;
    private String addressNamePlain;



    public Long getAddressId() {
        return addressId;
    }

    public void setAddressId(Long addressId) {
        this.addressId = addressId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getAddressName() {
        return addressName;
    }

    public void setAddressName(String addressName) {
        this.addressName = addressName;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getProvinceCode() {
        return provinceCode;
    }

    public void setProvinceCode(String provinceCode) {
        this.provinceCode = provinceCode;
    }

    public Long getCityCode() {
        return cityCode;
    }

    public void setCityCode(Long cityCode) {
        this.cityCode = cityCode;
    }

    public Long getRegionCode() {
        return regionCode;
    }

    public void setRegionCode(Long regionCode) {
        this.regionCode = regionCode;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public Long getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(Long createUserId) {
        this.createUserId = createUserId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Long getModifyUserId() {
        return modifyUserId;
    }

    public void setModifyUserId(Long modifyUserId) {
        this.modifyUserId = modifyUserId;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    public String getAddressNameEncrypt() {
        return addressNameEncrypt;
    }

    public void setAddressNameEncrypt(String addressNameEncrypt) {
        this.addressNameEncrypt = addressNameEncrypt;
    }

    public String getAddressNameChiper() {
        return addressNameChiper;
    }

    public void setAddressNameChiper(String addressNameChiper) {
        this.addressNameChiper = addressNameChiper;
    }

    public String getAddressNamePlain() {
        return addressNamePlain;
    }

    public void setAddressNamePlain(String addressNamePlain) {
        this.addressNamePlain = addressNamePlain;
    }
}
