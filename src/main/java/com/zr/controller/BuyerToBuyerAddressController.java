package com.zr.controller;

import com.zr.dataobject.BuyerAddress;
import com.zr.dataobject.BuyerInfo;
import com.zr.enums.ResultEnum;
import com.zr.exception.BuyException;
import com.zr.form.BuyerAddressForm;
import com.zr.service.BuyerAddressService;
import com.zr.service.BuyerInfoService;
import com.zr.utils.ResultVoUtil;
import com.zr.vo.ResultVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Auther: Administrator (zhangrun macmanboy@foxmail.com)
 * @Date: 2019/4/10 20:47
 * @Description:
 */
@RestController
@RequestMapping("/buyer/address")
@Slf4j
public class BuyerToBuyerAddressController {
    @Autowired
    private BuyerAddressService buyerAddressService;

    @Autowired
    private BuyerInfoService buyerInfoService;
    //创建地址
    @PostMapping("/save")
    public ResultVo<Map<String,String>> save(@Valid BuyerAddressForm buyerAddressForm, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            log.error("【创建买家地址信息有误】，buyerAddressForm={}",buyerAddressForm);
            throw new BuyException(ResultEnum.ADDRESS_NOT_PASS.getStatus(),bindingResult.getFieldError().getDefaultMessage());
        }
        BuyerAddress buyerAddress = new BuyerAddress();
        BeanUtils.copyProperties(buyerAddressForm,buyerAddress);
        Map<String,String> map = new HashMap<>();
        map.put("id",buyerAddressService.save(buyerAddress).getId());
        return ResultVoUtil.success(map);
    }

    @PostMapping("/delete")
    public ResultVo delete(@RequestParam("openid") String openid,@RequestParam("id") String id){
        buyerAddressService.delete(id);
        return ResultVoUtil.success();
    }


   /* @RequestParam(value = "page", defaultValue = "0") Integer page,
    @RequestParam(value = "size", defaultValue = "10") Integer size*/
    @GetMapping("/list")
    public ResultVo<List<BuyerAddressForm>> list(@RequestParam("openid") String openid){
        if (StringUtils.isEmpty(openid)){
            log.error("【openid为空】");
            throw new BuyException(ResultEnum.PARAM_ERROR);
        }
        BuyerInfo buyerInfo = buyerInfoService.findBuyerInfoByOpenid(openid);
        List<BuyerAddressForm> buyerAddressFormList = buyerAddressService.findAllByBuyerInfo(buyerInfo);
//        PageRequest pageRequest = new PageRequest(page,size);
//        Page<BuyerAddressForm> buyerAddressFormPage = buyerAddressService.findBuyerAddressByBuyerInfo(buyerInfo,pageRequest);
        return  ResultVoUtil.success(buyerAddressFormList);
    }
}
