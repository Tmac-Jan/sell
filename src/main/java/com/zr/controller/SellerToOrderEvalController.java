package com.zr.controller;

import com.zr.converter.OrderEvalToOrderEvalFormConverter;
import com.zr.dataobject.OrderEval;
import com.zr.dataobject.SellerInfo;
import com.zr.dataobject.ShopInfo;
import com.zr.enums.ResultEnum;
import com.zr.exception.SellException;
import com.zr.form.OrderEvalForm;
import com.zr.service.OrderEvalService;
import com.zr.service.SellerInfoService;
import com.zr.service.ShopInfoService;
import lombok.extern.slf4j.Slf4j;
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
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.Map;

/**
 * @Auther: Administrator (zhangrun macmanboy@foxmail.com)
 * @Date: 2019/3/22 19:39
 * @Description:
 */
@Controller
@RequestMapping("/seller/orderEval")
@Slf4j
public class SellerToOrderEvalController {
    @Autowired
    private OrderEvalService orderEvalService;
    @Autowired
    private SellerInfoService sellerInfoService;
    @Autowired
    private ShopInfoService shopInfoService;
    @GetMapping("/list")
    public ModelAndView list(@RequestParam(value = "page", defaultValue = "1") Integer page,
                             @RequestParam(value = "size", defaultValue = "10") Integer size,
                             Map<String, Object> map) {
        PageRequest request = new PageRequest(page - 1, size);
        SellerInfo sellerInfo = sellerInfoService.findSellerInfoById("2111");
        ShopInfo shopInfo = shopInfoService.findBySellerInfo(sellerInfo);
        // Page<BuyerComplain> buyerComplainPage = buyerComplainService.findByBuyerShopId(shopInfo.getId(), request);
        Page<OrderEvalForm> orderEvalFormPage = orderEvalService.getOrderEvalFormByShopId(shopInfo.getId(),request);

        map.put("orderEvalFormPage", orderEvalFormPage);
        map.put("currentPage", page);
        map.put("size", size);
        return new ModelAndView("orderEval/list", map);
    }
    @GetMapping("/index")
    public ModelAndView index(@RequestParam(value = "id", required = false) String id,
                              Map<String, Object> map) {
        if (!StringUtils.isEmpty(id)) {
            OrderEval orderEval = orderEvalService.findOne(id);
            try {
                if (orderEval == null){
                   throw new SellException(ResultEnum.ORDER_EVAL_NOT_EXIST);
                }
            }catch (SellException se){//订单评价不存在
                log.error("【卖家端评价发生异常】发生异常{}", se);
                map.put("msg", se.getMessage());
                map.put("url", "/sell/seller/orderEval/list");
                return new ModelAndView("common/error", map);
            }
            OrderEvalForm orderEvalForm = OrderEvalToOrderEvalFormConverter.convert(orderEval);
            map.put("orderEvalForm", orderEvalForm);
        }

        return new ModelAndView("orderEval/index", map);
    }
    @PostMapping("/reply")
    public ModelAndView reply(@Valid OrderEvalForm orderEvalForm, BindingResult bindingResult,Map<String,Object> map){
        if (bindingResult.hasErrors()) {
            map.put("msg", bindingResult.getFieldError().getDefaultMessage());
            map.put("url", "/sell/seller/orderEval/index");
            return new ModelAndView("common/error", map);
        }
        OrderEval orderEval = orderEvalService.findOne(orderEvalForm.getId());
        try {
            if (orderEval == null){
                throw  new SellException(ResultEnum.ORDER_EVAL_NOT_EXIST);
            }
        }catch (SellException se){
            map.put("msg", se.getMessage());
            map.put("url", "/sell/seller/orderEval/index");
            return new ModelAndView("common/error", map);
        }
        orderEval.setShopReply(orderEvalForm.getShopReply());
        orderEvalService.save(orderEval);
        map.put("url", "/sell/seller/orderEval/list");
        return new ModelAndView("common/success", map);
    }
}
