package com.zr.controller;

import com.lly835.bestpay.model.PayResponse;
import com.zr.dto.OrderDTO;
import com.zr.enums.ResultEnum;
import com.zr.exception.SellException;
import com.zr.service.OrderService;
import com.zr.service.PayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

/**
 * @Auther: Administrator (zhangrun macmanboy@foxmail.com)
 * @Date: 2019/3/14 19:23
 * @Description:
 */
@Controller
@RequestMapping("/pay")
public class PayController {
    @Autowired
    private OrderService orderService;

    @Autowired
    private PayService payService;
    @GetMapping("/create")
    public String create(@RequestParam("orderId") String orderId,
                               @RequestParam("returnUrl") String returnUrl,
                               Map<String, Object> map) {
        //1. 查询订单
        OrderDTO orderDTO = orderService.findOne(orderId);
        if (orderDTO == null) {
            throw new SellException(ResultEnum.ORDER_NOT_EXIST);
        }

        //2. 发起支付
        //PayResponse payResponse = payService.create(orderDTO);

//        map.put("payResponse", payResponse);
//        map.put("returnUrl", returnUrl);
        //修改订单的支付状态
        orderService.paid(orderDTO);
        return "redirect:" + returnUrl;
        //return new ModelAndView("pay/create", map);
    }
    /**
     * 微信异步通知
     * @param notifyData
     */
    @PostMapping("/notify")
    public ModelAndView notify(@RequestBody String notifyData) {
        payService.notify(notifyData);

        //返回给微信处理结果
        return new ModelAndView("pay/success");
    }
}
