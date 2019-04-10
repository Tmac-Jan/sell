package com.zr.controller;

import com.zr.converter.ShopInfoToShopInfoFormConvert;
import com.zr.converter.ShopRequestFormConverter;
import com.zr.dataobject.SellerInfo;
import com.zr.dataobject.ShopInfo;
import com.zr.dataobject.ShopRequest;
import com.zr.enums.ResultEnum;
import com.zr.enums.ShopOpenStatus;
import com.zr.enums.ShopRequestResultEnum;
import com.zr.enums.ShopStatusEnum;
import com.zr.exception.NoShopException;
import com.zr.exception.SellException;
import com.zr.form.ShopInfoForm;
import com.zr.form.ShopRequestForm;
import com.zr.service.SellerInfoService;
import com.zr.service.ShopInfoService;
import com.zr.utils.EnumUtil;
import com.zr.utils.GetTokenValueUtil;
import com.zr.utils.RandomUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Map;

/**
 * @Auther: Administrator (zhangrun macmanboy@foxmail.com)
 * @Date: 2019/3/25 13:50
 * @Description:
 */
@Controller
@RequestMapping("/seller/shop")
@Slf4j
public class SellerToShopController {
    @Autowired
    private ShopInfoService shopInfoService;

    @Autowired
    private SellerInfoService sellerInfoService;

    @GetMapping("/shopInfo")
    public ModelAndView myShopInfo(Map<String, Object> map) {

        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
                .getRequestAttributes()).getRequest();
        String sellerOpenid = GetTokenValueUtil.getToken(request);
        SellerInfo sellerInfo = sellerInfoService.findSellerInfoByOpenid(sellerOpenid);
        ShopInfo shopInfo = shopInfoService.findBySellerInfo(sellerInfo);
        try {
            if (shopInfo != null) {
                map.put("shopId", shopInfo.getId());
                map.put("shopName", shopInfo.getShopName());
                map.put("shopExpressTime", shopInfo.getShopExpressTime());
                map.put("shopScore", shopInfo.getShopScore());
                map.put("shopOpenStatus", EnumUtil.getByCode(shopInfo.getShopOpen(), ShopOpenStatus.class).getMessage());
            } else {
                throw new NoShopException();
            }
        } catch (NoShopException e) {
            log.error("【卖家端无法找到相应店铺异常】", e);
            map.put("msg", e.getMessage());
            map.put("url", "/sell/seller/order/list");
            return new ModelAndView("common/error", map);

        }
        return new ModelAndView("home/home", map);
    }

    @GetMapping("/index")
    public ModelAndView index(Map<String, Object> map) {
        //获取当前登录的用户，从而获取他所拥有的店铺
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
                .getRequestAttributes()).getRequest();
        String sellerOpenid = GetTokenValueUtil.getToken(request);
        SellerInfo sellerInfo = sellerInfoService.findSellerInfoByOpenid(sellerOpenid);
        ShopInfo shopInfo = shopInfoService.findBySellerInfo(sellerInfo);
        ShopInfoForm shopInfoForm = ShopInfoToShopInfoFormConvert.convert(shopInfo);
        map.put("shopInfoForm", shopInfoForm);
        return new ModelAndView("home/index", map);
    }

  @PostMapping("/save")
    public ModelAndView reply(@Valid ShopInfoForm shopInfoForm, BindingResult bindingResult, Map<String, Object> map) {
        if (bindingResult.hasErrors()) {
            map.put("msg", bindingResult.getFieldError().getDefaultMessage());
            map.put("url", "/sell/seller/request/index");
            return new ModelAndView("common/error", map);
        }
        ShopInfo shopInfo = null;
        try{
            if (!StringUtils.isEmpty(shopInfoForm.getId())){
                shopInfo = shopInfoService.findOne(shopInfoForm.getId());
                if (shopInfo==null){
                    throw  new NoShopException();
                }
            }else{
                throw  new NoShopException();
            }
        }catch (NoShopException e){
            log.error("【卖家端店铺信息发生异常】{}",e);
            map.put("msg", e.getMessage());
            map.put("url", "/sell/seller/shop/shopInfo");
            return new ModelAndView("common/error", map);
        }
        BeanUtils.copyProperties(shopInfoForm,shopInfo);
        shopInfoService.save(shopInfo);
        map.put("url", "/sell/seller/shop/shopInfo");
        return new ModelAndView("common/success", map);
    }
}
