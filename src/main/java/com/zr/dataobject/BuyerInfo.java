package com.zr.dataobject;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Auther: Administrator (zhangrun macmanboy@foxmail.com)
 * @Date: 2019/3/12 10:52
 * @Description:
 */
@Entity
//@Data
@DynamicUpdate
@Table(name = "buyer_info")
public class BuyerInfo {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private Integer id;

    @Column(name = "username")
    private String username;
    @Column(name = "password")
    private String password;
    @Column(name = "openid")
    private String openid;
    @Column(name = "create_time")
    private Date createTime;
    @Column(name = "update_time")
    private Date updateTime;

    @OneToMany(mappedBy ="buyerInfo",targetEntity = BuyerAddress.class,cascade=CascadeType.ALL,fetch=FetchType.LAZY)
   // @JsonIgnoreProperties("buyerInfo")
    private List<BuyerAddress> buyerAddressList = new ArrayList<>(0);
    public BuyerInfo(String username, String password, String openid) {
        this.username = username;
        this.password = password;
        this.openid = openid;
    }

    public BuyerInfo(){}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    @JsonIgnore
    public List<BuyerAddress> getBuyerAddressList() {
        return buyerAddressList;
    }

    public void setBuyerAddressList(List<BuyerAddress> buyerAddressList) {
        this.buyerAddressList = buyerAddressList;
    }
}
