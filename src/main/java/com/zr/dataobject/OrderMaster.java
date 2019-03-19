package com.zr.dataobject;

import com.zr.enums.OrderStatusEnum;
import com.zr.enums.PayStatusEnum;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @Auther: Administrator (zhangrun macmanboy@foxmail.com)
 * @Date: 2019/3/12 10:50
 * @Description:
 */
@Entity
@Data
@DynamicUpdate
public class OrderMaster {
    /**
     * 订单id.
     */
    @Id
    private String orderId;

    /**
     * 买家名字.
     */
    private String buyerName;


    private String buyerPhone;


    private String buyerAddress;

    /**
     * 买家微信Openid.
     */
    private String buyerOpenid;

    /**
     * 订单总金额.
     */
    private BigDecimal orderAmount;

    /**
     * 订单状态, 默认为0 新下单.
     */
    private Integer orderStatus = OrderStatusEnum.NEW.getStatus();

    /**
     * 支付状态, 默认为0 未支付.
     */
    private Integer payStatus = PayStatusEnum.WAIT.getStatus();

    //商家Id
    private String shopId;

    private Date createTime;

    private Date updateTime;
}
