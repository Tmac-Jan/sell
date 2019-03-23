package com.zr.dataobject;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.criteria.CriteriaBuilder;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @Auther: Administrator (zhangrun macmanboy@foxmail.com)
 * @Date: 2019/3/12 10:52
 * @Description:
 */
@Data
@Entity
@DynamicUpdate
public class ShopInfo {
    @Id
    private String id;
    private String sellerOpenid;
    private String shopAddress;
    private String shopPhone;
    private String shopType;  //商店类型 比如卖汉堡的 还是日料的 等等
    private String shopIntro; //商店介绍
    private BigDecimal shopDeliver; //配送费
    private String shopIcon;
    private String shopFile;
    private Integer shopOpen; //是否营业
    private Integer shopBlock;//是否被封店
    private Integer shopExpressTime;//平均配送时间
    private Integer shopScore;//商店评分
    private String shopName;//商店名字
    private Date createTime;
    private Date updateTime;
    @OneToOne(targetEntity = SellerInfo.class)
    @JoinColumn(name = "seller_id")
    private SellerInfo sellerInfo;

    public ShopInfo() {
    }
}
