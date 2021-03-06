package com.zr.controller;

import com.zr.converter.OrderFormToOrderDTOConverter;
import com.zr.dataobject.BuyerAddress;
import com.zr.dataobject.BuyerComplain;
import com.zr.dataobject.OrderEval;
import com.zr.dto.OrderDTO;
import com.zr.enums.OptionStatusEnum;
import com.zr.enums.ResultEnum;
import com.zr.exception.SellException;
import com.zr.form.OrderForm;
import com.zr.service.*;
import com.zr.utils.ResultVoUtil;
import com.zr.vo.ResultVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Auther: Administrator (zhangrun macmanboy@foxmail.com)
 * @Date: 2019/3/13 16:30
 * @Description:
 */
@RestController
@RequestMapping("/buyer/order")
@Slf4j
public class BuyerToOrderController {
    @Autowired
    private OrderService orderService;

    @Autowired
    private BuyService buyService;

    @Autowired
    private BuyerAddressService buyerAddressService;

    @Autowired
    private BuyerComplainService buyerComplainService;

    @Autowired
    private OrderEvalService orderEvalService;

    //创建订单
    @PostMapping("/create")
    public ResultVo<Map<String, String>> create(@Valid OrderForm orderForm,
                                                BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            log.error("【创建DLM订单】参数不正确, orderForm={}", orderForm);
            throw new SellException(ResultEnum.PARAM_ERROR.getStatus(),
                    bindingResult.getFieldError().getDefaultMessage());
        }
        BuyerAddress buyerAddress = buyerAddressService.findBuyerAddressByIdAndOpenid(orderForm.getAddressId(),orderForm.getOpenid());
        orderForm.setName(buyerAddress.getBuyerName());
        orderForm.setPhone(buyerAddress.getBuyerPhone());
        orderForm.setAddress(buyerAddress.getBuyerAddress());
        OrderDTO orderDTO = OrderFormToOrderDTOConverter.convert(orderForm);
        if (CollectionUtils.isEmpty(orderDTO.getOrderDetailList())) {
            log.error("【创建DLM订单】购物车内的商品不能为空");
            throw new SellException(ResultEnum.CART_EMPTY);
        }
        orderDTO.setShopId("201931901");
        OrderDTO createResult = orderService.create(orderDTO);

        Map<String, String> map = new HashMap<>();
        map.put("orderId", createResult.getOrderId());

        return ResultVoUtil.success(map);
    }
    //订单列表
    @GetMapping("/list")
    public ResultVo<List<OrderDTO>> list(@RequestParam("openid") String openid,
                                         @RequestParam(value = "page", defaultValue = "0") Integer page,
                                         @RequestParam(value = "size", defaultValue = "10") Integer size) {
        if (StringUtils.isEmpty(openid)) {
            log.error("【查询订单列表】openid为空");
            throw new SellException(ResultEnum.PARAM_ERROR);
        }

        PageRequest request = new PageRequest(page, size);
        Page<OrderDTO> orderDTOPage = orderService.findList(openid, request);

        return ResultVoUtil.success(orderDTOPage.getContent());
    }
    //订单详情
    @GetMapping("/detail")
    public ResultVo<OrderDTO> detail(@RequestParam("openid") String openid,
                                     @RequestParam("orderId") String orderId) {


        OrderDTO orderDTO = buyService.findOrderOne(openid, orderId);

        OrderEval orderEval = orderEvalService.findByBuyerOpenidAndOrderId(openid,orderId);
        if (orderEval!=null){
            orderDTO.setEvalStatus(OptionStatusEnum.DONE.getStatus());
            orderDTO.setBuyerEval(orderEval.getBuyerEval());
            orderDTO.setEvalPhoto(orderEval.getEvalPhoto());
        }
        BuyerComplain buyerComplain = buyerComplainService.findByBuyerOpenidAndOrderId(openid,orderId);
        if (buyerComplain!=null){
            orderDTO.setComStatus(OptionStatusEnum.DONE.getStatus());
            orderDTO.setComplainFile(buyerComplain.getComplainFile());
            orderDTO.setComplainPhoto(buyerComplain.getComplainPhoto());
            orderDTO.setBuyerComplain(buyerComplain.getBuyerComplain());
        }
        return ResultVoUtil.success(orderDTO);
    }

    //取消订单
    @PostMapping("/cancel")
    public ResultVo cancel(@RequestParam("openid") String openid,
                           @RequestParam("orderId") String orderId) {
        buyService.cancelOrder(openid, orderId);
        return ResultVoUtil.success();
    }
}
