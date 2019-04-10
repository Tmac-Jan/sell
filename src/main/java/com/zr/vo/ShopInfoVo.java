package com.zr.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.zr.enums.ShopOpenStatus;
import com.zr.enums.ShopStatusEnum;
import com.zr.utils.EnumUtil;
import com.zr.utils.serializer.Date2LongSerializer;
import lombok.Data;

import java.util.Date;

/**
 * @Auther: Administrator (zhangrun macmanboy@foxmail.com)
 * @Date: 2019/3/30 09:39
 * @Description:
 */
@Data
public class ShopInfoVo {
    private String id;
    private String sellerOpenid;
    private String shopName;//商店名字
    private String shopAddress;
    private String shopPhone;
//    private String shopType;  //商店类型 比如卖汉堡的 还是日料的 等等
//    private String shopIntro; //商店介绍
//    private BigDecimal shopDeliver; //配送费
    private String shopIcon;
//    private String shopFile;
    private Integer shopOpen; //是否营业
    private Integer shopBlock;//是否被封店

    @JsonSerialize(using = Date2LongSerializer.class)
    private Date createTime;
    @JsonSerialize(using = Date2LongSerializer.class)
    private Date updateTime;

    @JsonIgnore
    public ShopStatusEnum getShopStatusEnum(){
        return EnumUtil.getByCode(shopBlock,ShopStatusEnum.class);
    }

    @JsonIgnore
    public ShopOpenStatus getShopOpenStausEnum(){
        return EnumUtil.getByCode(shopOpen,ShopOpenStatus.class);
    }
}
