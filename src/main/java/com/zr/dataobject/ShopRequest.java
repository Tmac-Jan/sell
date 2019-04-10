package com.zr.dataobject;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

/**
 * @Auther: Administrator (zhangrun macmanboy@foxmail.com)
 * @Date: 2019/3/21 22:15
 * @Description:
 */
@Data
@Entity
@DynamicUpdate
public class ShopRequest {
    @Id
    private String id;
    private String sellerId;
    private String sellerOpenid;
    private String shopAddress;
    private String shopPhone;
    private String shopType;
    private String shopName;
    private Integer requestType;
    private String requestContent;
    private String requestImg;
    private String licenseImg;
    private String healthImg;
    private Integer requestResult;
    private String notifyEmail;
    private Date createTime;
    private Date updateTime;
}
