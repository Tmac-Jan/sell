package com.zr.form;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.zr.utils.serializer.Date2LongSerializer;
import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @Auther: Administrator (zhangrun macmanboy@foxmail.com)
 * @Date: 2019/3/29 19:34
 * @Description:
 */
@Data
public class ShopInfoForm {
    @NotEmpty(message = "申请id必填")
    private String id;
    @NotEmpty(message = "卖家openid必填")
    private String sellerOpenid;
    @NotEmpty(message = "商店地址必填")
    private String shopAddress;
    @NotEmpty(message = "商店电话必填")
    private String shopPhone;
  //  private String shopType;  //商店类型 比如卖汉堡的 还是日料的 等等
  @NotEmpty(message = "商店介绍必填")
    private String shopIntro; //商店介绍
    @NotNull(message = "配送费必填")
    private BigDecimal shopDeliver; //配送费
    @NotEmpty(message = "商店图标必填")
    private String shopIcon;
    @NotEmpty(message = "营业资质图必填")
    private String shopFile;

    @NotNull(message = "营业状态必填")
    private Integer shopOpen ; //是否营业
   // private Integer shopBlock;//是否被封店
    //private Integer shopExpressTime;//平均配送时间
   // private BigDecimal shopScore;//商店评分
    @NotEmpty(message = "商店名字必填")
    private String shopName;//商店名字

    @JsonSerialize(using = Date2LongSerializer.class)
    private Date createTime;
    @JsonSerialize(using = Date2LongSerializer.class)
    private Date updateTime;
}
