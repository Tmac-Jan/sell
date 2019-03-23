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
    @GeneratedValue
    private Integer id;
    private String buyerId;
    private String buyerOpenid;
    private String buyEval;
    private Integer score;
    private String recommend;
    private Integer rateType;
    private Integer deliverTime;
    private String orderId;
    private String shopReply;
    private String evalPhoto;
    private String shopId;
    private Date createTime;
    private Date updateTime;
}
