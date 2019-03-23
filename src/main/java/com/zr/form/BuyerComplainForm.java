package com.zr.form;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.zr.enums.BuyerComplainEnum;
import com.zr.utils.EnumUtil;
import com.zr.utils.serializer.Date2LongSerializer;
import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @Auther: Administrator (zhangrun macmanboy@foxmail.com)
 * @Date: 2019/3/22 21:53
 * @Description:
 */
@Data
public class BuyerComplainForm {

    private String id;
    private Integer buyerId;
    @NotEmpty(message = "买家Openid必填")
    private String buyerOpenid;

    @NotEmpty(message = "订单编号必填")
    private String orderId;

    @NotEmpty(message = "买家投诉内容必填")
    private String buyerComplain;

    private String complainFile;
    private String shopReply;
    private String supportReply;
    private Integer supportId;
    private Integer state;
    private String complainPhoto;
    @NotEmpty(message = "商店编号必填")
    private String shopId;
    // 创建时间
    @JsonSerialize(using = Date2LongSerializer.class)
    private Date createTime;
    // 更新时间
    @JsonSerialize(using = Date2LongSerializer.class)
    private Date updateTime;

    @JsonIgnore
    public BuyerComplainEnum getBuyerComplainEnum(){
        return EnumUtil.getByCode(state,BuyerComplainEnum.class);
    }

}
