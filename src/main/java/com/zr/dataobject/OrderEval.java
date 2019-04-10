package com.zr.dataobject;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

/**
 * @Auther: Administrator (zhangrun macmanboy@foxmail.com)
 * @Date: 2019/3/22 19:41
 * @Description:
 */
@Entity
@Data
@DynamicUpdate
public class OrderEval {
    @Id
    private String id;
    private String buyerId;
    private String buyerOpenid;
    private String buyerEval;
    private Integer score;
    private String recommend;
    private Integer rateType;
    private Integer deliveryTime;
    private String orderId;
    private String shopReply;
    private String evalPhoto;
    private String shopId;
    private Date createTime;
    private Date updateTime;
}
