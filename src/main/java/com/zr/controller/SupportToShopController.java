package com.zr.controller;

import com.zr.converter.AcInfoToAcInfoFormConverter;
import com.zr.converter.ShopInfoToShopInfoFormConvert;
import com.zr.converter.ShopInfoToShopInfoVoConverter;
import com.zr.dataobject.AcInfo;
import com.zr.dataobject.ShopInfo;
import com.zr.exception.NoAdException;
import com.zr.exception.NoShopException;
import com.zr.form.AcInfoForm;
import com.zr.service.ShopInfoService;
import com.zr.service.ShopRequestService;
import com.zr.vo.ShopInfoVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

/**
 * @Auther: Administrator (zhangrun macmanboy@foxmail.com)
 * @Date: 2019/3/25 13:20
 * @Description:
 */
@Controller
@RequestMapping("/support/shop")
@Slf4j
public class SupportToShopController {
    @Autowired
    private ShopInfoService shopInfoService;

    @GetMapping("/list")
    public ModelAndView list(@RequestParam(value = "page", defaultValue = "1") Integer page,
                             @RequestParam(value = "size", defaultValue = "10") Integer size,
                             Map<String, Object> map) {
        PageRequest request = new PageRequest(page - 1, size);
        Page<ShopInfo> shopInfoPage = shopInfoService.findAll(request);
        List<ShopInfo> shopInfoList =shopInfoPage.getContent();
        Page<ShopInfoVo> shopInfoVoPage = new PageImpl<ShopInfoVo>(ShopInfoToShopInfoVoConverter.convert(shopInfoList),request,shopInfoPage.getTotalElements());
        map.put("shopInfoVoPage", shopInfoVoPage);
        map.put("currentPage", page);
        map.put("size", size);
        return new ModelAndView("support/shop/list", map);
    }

//    @GetMapping("/index")
//    public ModelAndView index(@RequestParam(value = "id") String id,
//                              Map<String, Object> map) {
//        if (!StringUtils.isEmpty(id)) {
//            ShopInfo shopInfo = shopInfoService.findOne(id);
//            try {
//                if (shopInfo == null) {
//                    throw new NoShopException();
//                }
//            } catch (NoShopException e) {//商店不存在异常
//                log.error("【商店不存在异常】发生异常{}", e);
//                map.put("msg", e.getMessage());
//                map.put("url", "/sell/support/shop/list");
//                return new ModelAndView("common/error", map);
//            }
//            map.put("shopInfo", shopInfo);
//        }
//
//        return new ModelAndView("support/shop/index", map);
//    }

    @GetMapping("/blockShop")
    public ModelAndView blockShop(@RequestParam(value = "id") String id,
                                  Map<String, Object> map) {
        try {
            if (!StringUtils.isEmpty(id)) {
                ShopInfo shopInfo = shopInfoService.findOne(id);
                if (shopInfo == null) {
                    throw new NoShopException();
                }
                map.put("url", "/sell/support/shop/list");
                shopInfoService.block(shopInfo);
            } else {
                throw new NoShopException();
            }
        } catch (NoShopException e) {//商店不存在异常
            log.error("【商店不存在异常】发生异常{}", e);
            map.put("msg", e.getMessage());
            map.put("url", "/sell/support/shop/list");
            return new ModelAndView("common/error", map);
        }
        return new ModelAndView("common/success", map);
    }

    @GetMapping("/reOpenShop")
    public ModelAndView reOpenShop(@RequestParam(value = "id") String id,
                                  Map<String, Object> map) {
        try {
            if (!StringUtils.isEmpty(id)) {
                ShopInfo shopInfo = shopInfoService.findOne(id);
                if (shopInfo == null) {
                    throw new NoShopException();
                }
                map.put("url", "/sell/support/shop/list");
                shopInfoService.block(shopInfo);
            } else {
                throw new NoShopException();
            }
        } catch (NoShopException e) {//商店不存在异常
            log.error("【商店不存在异常】发生异常{}", e);
            map.put("msg", e.getMessage());
            map.put("url", "/sell/support/shop/list");
            return new ModelAndView("common/error", map);
        }
        return new ModelAndView("common/success", map);
    }
}
