package com.zr.controller;

import com.zr.converter.OrderEvalToOrderEvalFormConverter;
import com.zr.dataobject.OrderEval;
import com.zr.dto.OrderDTO;
import com.zr.enums.ResultEnum;
import com.zr.exception.SellException;
import com.zr.form.OrderEvalForm;
import com.zr.service.OrderEvalService;
import com.zr.service.OrderService;
import com.zr.utils.ResultVoUtil;
import com.zr.vo.ResultVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

/**
 * @Auther: Administrator (zhangrun macmanboy@foxmail.com)
 * @Date: 2019/4/13 23:50
 * @Description:
 */
@RestController
@RequestMapping("/buyer/orderEval")
@Slf4j
public class BuyerToOrderEvalController {
    @Autowired
    private OrderService orderService;
    @Autowired
    private OrderEvalService orderEvalService;

    @PostMapping("/create")
    public ResultVo<Map<String,String>> create(@Valid OrderEvalForm orderEvalForm, BindingResult bindingResult){
        if(bindingResult.hasErrors()||!(StringUtils.isEmpty(orderEvalForm.getId()))){
            log.error("创建评论参数不正确，orderEval={}",orderEvalForm);
            throw  new SellException(ResultEnum.PARAM_ERROR.getStatus(),bindingResult.getFieldError().getDefaultMessage());
        }
        OrderDTO orderDTO = orderService.findOne(orderEvalForm.getOrderId());
        OrderEval orderEval =  new OrderEval();
        BeanUtils.copyProperties(orderEvalForm,orderEval);
        orderEval.setShopId(orderDTO.getShopId());
        orderEvalService.save(orderEval);
        return ResultVoUtil.success();
    }

    //取消订单
    @PostMapping("/delete")
    public ResultVo delete(@RequestParam("openid") String openid,
                           @RequestParam("orderId") String orderId) {
        //Todo
        OrderEval orderEval = orderEvalService.findByBuyerOpenidAndOrderId(openid,orderId);
        orderEvalService.delete(orderEval);
       // buyService.cancelOrder(openid, orderId);
        return ResultVoUtil.success();
    }

    //评价详情
    @GetMapping("/detail")
    public ResultVo<OrderDTO> detail(@RequestParam("openid") String openid,
                                     @RequestParam("orderId") String orderId) {
        OrderEval orderEval = orderEvalService.findByBuyerOpenidAndOrderId(openid,orderId);
        OrderEvalForm orderEvalForm = OrderEvalToOrderEvalFormConverter.convert(orderEval);
        return ResultVoUtil.success(orderEvalForm);
    }
}
