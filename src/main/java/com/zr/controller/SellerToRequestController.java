package com.zr.controller;

import com.zr.converter.ShopRequestFormConverter;
import com.zr.dataobject.SellerInfo;
import com.zr.dataobject.ShopRequest;
import com.zr.enums.ResultEnum;
import com.zr.enums.ShopRequestResultEnum;
import com.zr.enums.ShopRequestTypeEnum;
import com.zr.exception.SellException;
import com.zr.form.ShopRequestForm;
import com.zr.service.SellerInfoService;
import com.zr.service.ShopRequestService;
import com.zr.utils.GetTokenValueUtil;
import com.zr.utils.RandomUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
 * @Date: 2019/3/28 22:24
 * @Description:
 */
@Controller
@RequestMapping("/seller/request")
@Slf4j
public class SellerToRequestController {
    @Autowired
    ShopRequestService shopRequestService;
    @Autowired
    SellerInfoService sellerInfoService;

    @GetMapping("/list")
    public ModelAndView list(@RequestParam(value = "page", defaultValue = "1") Integer page,
                             @RequestParam(value = "size", defaultValue = "10") Integer size,
                             Map<String, Object> map) {
        PageRequest pageRequest = new PageRequest(page - 1, size);
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
                .getRequestAttributes()).getRequest();
        String sellerOpenid = GetTokenValueUtil.getToken(request);
        SellerInfo sellerInfo = sellerInfoService.findSellerInfoByOpenid(sellerOpenid);
        Page<ShopRequestForm> shopRequestFormPage = shopRequestService.getAllBySellerOpenid(sellerInfo.getOpenid(), pageRequest);

        map.put("shopRequestFormPage", shopRequestFormPage);
        map.put("currentPage", page);
        map.put("size", size);
        return new ModelAndView("request/list", map);
    }

    @GetMapping("/index")
    public ModelAndView index(@RequestParam(value = "id", required = false) String id,
                              Map<String, Object> map) {
        if (!StringUtils.isEmpty(id)) {
            ShopRequest shopRequest = shopRequestService.findOne(id);
            try {
                if (shopRequest == null) {
                    throw new SellException(ResultEnum.REQUEST_NOT_EXIST);
                }
            } catch (SellException se) {//申请不存在
                log.error("【卖家端申请发生异常】发生异常{}", se);
                map.put("msg", se.getMessage());
                map.put("url", "/sell/seller/request/list");
                return new ModelAndView("common/error", map);
            }
            ShopRequestForm shopRequestForm = ShopRequestFormConverter.convert(shopRequest);

            map.put("shopRequestForm", shopRequestForm);
        }
        return new ModelAndView("request/index", map);
    }

    @GetMapping("/cancel")
    public ModelAndView cancel(@RequestParam(value = "id") String id,
                               Map<String, Object> map) {
        if (!StringUtils.isEmpty(id)) {
            ShopRequest shopRequest = shopRequestService.findOne(id);
            try {
                if (shopRequest == null) {
                    throw new SellException(ResultEnum.REQUEST_NOT_EXIST);
                }
            } catch (SellException se) {//申请不存在
                log.error("【卖家端申请发生异常】发生异常{}", se);
                map.put("msg", se.getMessage());
                map.put("url", "/sell/seller/request/list");
                return new ModelAndView("common/error", map);
            }
            shopRequestService.cancelRequest(shopRequest);
        }
        //TODO
        //ShopRequestForm shopRequestForm = ShopRequestFormConverter.convert(shopRequest);
        // map.put("shopRequestForm", shopRequestForm);
        map.put("url", "/sell/seller/request/list");
        return new ModelAndView("common/success", map);
    }

    @PostMapping("/save")
    public ModelAndView reply(@Valid ShopRequestForm shopRequestForm, BindingResult bindingResult, Map<String, Object> map) {
        if (bindingResult.hasErrors()) {
            map.put("msg", bindingResult.getFieldError().getDefaultMessage());
            map.put("url", "/sell/seller/request/index");
            return new ModelAndView("common/error", map);
        }
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
                .getRequestAttributes()).getRequest();
        String sellerOpenid = GetTokenValueUtil.getToken(request);
        SellerInfo sellerInfo = sellerInfoService.findSellerInfoByOpenid(sellerOpenid);
        ShopRequest shopRequest = new ShopRequest();
        try {
            if (!StringUtils.isEmpty(shopRequestForm.getId())) {
                shopRequest = shopRequestService.findOne(shopRequestForm.getId());
                if (shopRequest == null)
                    throw new SellException(ResultEnum.REQUEST_NOT_EXIST);
                //TODO
                BeanUtils.copyProperties(shopRequestForm, shopRequest);
            } else {//申请为新建时
                BeanUtils.copyProperties(shopRequestForm, shopRequest);
                shopRequest.setId(RandomUtil.genUniqueKey());
                shopRequest.setRequestResult(ShopRequestResultEnum.REQUEST_NEW.getStatus());
                shopRequest.setSellerId(sellerInfo.getId());
                shopRequest.setSellerOpenid(sellerOpenid);
                System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!" + shopRequest.toString());
            }
            shopRequestService.save(shopRequest);
        } catch (SellException se) {
            map.put("msg", se.getMessage());
            map.put("url", "/sell/seller/request/index");
            return new ModelAndView("common/error", map);
        }
        map.put("url", "/sell/seller/request/list");
        return new ModelAndView("common/success", map);
    }
}
