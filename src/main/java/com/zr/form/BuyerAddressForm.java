package com.zr.form;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @Auther: Administrator (zhangrun macmanboy@foxmail.com)
 * @Date: 2019/4/10 20:34
 * @Description:
 */
@Data
public class BuyerAddressForm {

    private String id;

    @NotEmpty(message = "电话号码必填")
    private String buyerPhone;

    @NotNull(message = "性别必填")
    private Integer buyerGender;

    @NotEmpty(message = "地址必填")
    private String buyerAddress;

    @NotEmpty(message = "姓名")
    private String buyerName;

    @NotEmpty(message = "openid必填")
    private String openid;

    private Date createTime;

    private Date updateTime;
}
