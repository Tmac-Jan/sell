package com.zr.form;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.zr.enums.OrderEvalEnum;
import com.zr.utils.EnumUtil;
import com.zr.utils.serializer.Date2LongSerializer;
import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

import java.util.Date;

/**
 * @Auther: Administrator (zhangrun macmanboy@foxmail.com)
 * @Date: 2019/3/24 11:56
 * @Description:
 */
@Data
public class OrderEvalForm {

    private String id;
    private String buyerId;
    @NotEmpty(message = "买家Openid必填")
    private String buyerOpenid;
    @NotEmpty(message = "买家评论必填")
    private String buyerEval;
    private Integer score =5;
    private String recommend;
    //默认好评
    private Integer rateType = OrderEvalEnum.GOOD_EVAL.getStatus();
    @NotEmpty(message = "配送时间必填")
    private Integer deliveryTime;
    @NotEmpty(message = "订单编号必填")
    private String orderId;
    private String shopReply;
    private String evalPhoto;
    private String shopId;
    @JsonSerialize(using = Date2LongSerializer.class)
    private Date createTime;
    @JsonSerialize(using = Date2LongSerializer.class)
    private Date updateTime;

    @JsonIgnore
    public OrderEvalEnum getOrederEvalEnum(){
        return EnumUtil.getByCode(rateType,OrderEvalEnum.class);
    }
}
