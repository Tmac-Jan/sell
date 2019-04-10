package com.zr.form;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.zr.enums.ShopRequestResultEnum;
import com.zr.enums.ShopRequestTypeEnum;
import com.zr.utils.EnumUtil;
import com.zr.utils.serializer.Date2LongSerializer;
import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.JoinColumn;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @Auther: Administrator (zhangrun macmanboy@foxmail.com)
 * @Date: 2019/3/20 15:11
 * @Description:
 */
@Data
public class ShopRequestForm {
    private String id;

    private String sellerId;

    private String sellerOpenid;

    @NotEmpty(message = "商家地址必填")
    private String shopAddress;

    @NotEmpty(message = "商店电话必填")
    private String shopPhone;

    @NotEmpty(message = "商店类型必填")
    private String shopType;

    @NotEmpty(message = "商店名字必填")
    private String shopName;

    @NotNull(message = "申请类型必填")
    private Integer requestType;

    @NotEmpty(message = "申请内容必填")
    private String requestContent;

    private String requestImg;

    @NotEmpty(message = "营业执照必填")
    private String licenseImg;

    @NotEmpty(message = "卫生许可证必填")
    private String healthImg;

    private Integer requestResult;

    @NotEmpty(message = "通知邮箱必填")
    private String notifyEmail;

    @JsonSerialize(using = Date2LongSerializer.class)
    private Date createTime;
    @JsonSerialize(using = Date2LongSerializer.class)
    private Date updateTime;

    @JoinColumn
    public ShopRequestResultEnum getShopRequestResultEnum(){
        return EnumUtil.getByCode(requestResult,ShopRequestResultEnum.class);
    }

    @JoinColumn
    public ShopRequestTypeEnum getShopRequestTypeEnum(){
        return EnumUtil.getByCode(requestType,ShopRequestTypeEnum.class);
    }
}
