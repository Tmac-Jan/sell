package com.zr.dataobject;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

/**
 * @Auther: Administrator (zhangrun macmanboy@foxmail.com)
 * @Date: 2019/3/12 10:53
 * @Description:
 */
@Entity
@Data
@DynamicUpdate
public class BuyerComplain {
    @Id
    private String id;
    private Integer buyerId;
    private String buyerOpenid;
    private String orderId;
    private String buyerComplain;
    private String complainFile;
    private String shopReply;
    private String supportReply;
    private Integer supportId;
    private Integer state;
    private String complainPhoto;
    private String shopId;
    private Date createTime;
    private Date updateTime;


}
