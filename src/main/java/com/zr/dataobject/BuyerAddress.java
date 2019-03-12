package com.zr.dataobject;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.DynamicUpdate;
import javax.persistence.*;
import java.util.Date;

/**
 * @Auther: Administrator (zhangrun macmanboy@foxmail.com)
 * @Date: 2019/3/12 10:52
 * @Description:
 */
@Entity
//@Data
@DynamicUpdate
public class BuyerAddress {
    @Id
    @GeneratedValue
    private Integer id;

    private String buyerPhone;

    private Integer buyerGender;

    private String buyerAddress;

    private String openid;

    private Date createTime;

    private Date updateTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="buyer_id")
    //@JsonIgnore
    @JsonIgnoreProperties("buyerAddressList")
    private BuyerInfo buyerInfo;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBuyerPhone() {
        return buyerPhone;
    }

    public void setBuyerPhone(String buyerPhone) {
        this.buyerPhone = buyerPhone;
    }

    public Integer getBuyerGender() {
        return buyerGender;
    }

    public void setBuyerGender(Integer buyerGender) {
        this.buyerGender = buyerGender;
    }

    public String getBuyerAddress() {
        return buyerAddress;
    }

    public void setBuyerAddress(String buyerAddress) {
        this.buyerAddress = buyerAddress;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public BuyerInfo getBuyerInfo() {
        return buyerInfo;
    }

    public void setBuyerInfo(BuyerInfo buyerInfo) {
        this.buyerInfo = buyerInfo;
    }
}
