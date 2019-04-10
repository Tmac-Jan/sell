package com.zr.converter;

import com.zr.dataobject.ShopInfo;
import com.zr.vo.ShopInfoVo;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @Auther: Administrator (zhangrun macmanboy@foxmail.com)
 * @Date: 2019/3/30 09:45
 * @Description:
 */
public class ShopInfoToShopInfoVoConverter {
    public static ShopInfoVo convert(ShopInfo shopInfo){
       ShopInfoVo shopInfoVo = new ShopInfoVo();
        BeanUtils.copyProperties(shopInfo,shopInfoVo);
        return  shopInfoVo;
    }
    public static List<ShopInfoVo> convert(List<ShopInfo> shopInfoList){
        return  shopInfoList.stream().map(e->convert(e)).collect(Collectors.toList());
    }
}
