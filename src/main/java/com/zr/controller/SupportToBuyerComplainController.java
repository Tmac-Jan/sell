package com.zr.controller;

import com.zr.dataobject.BuyerComplain;
import com.zr.enums.BuyerComplainEnum;
import com.zr.enums.ResultEnum;
import com.zr.exception.SellException;
import com.zr.form.BuyerComplainForm;
import com.zr.service.BuyerComplainService;
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
 * @Date: 2019/4/2 20:47
 * @Description:
 */

@Controller
@RequestMapping("/support/buyerComplain")
@Slf4j
public class SupportToBuyerComplainController {
    @Autowired
    private BuyerComplainService buyerComplainService;

    @GetMapping("/list")
    public ModelAndView list(@RequestParam(value = "page", defaultValue = "1") Integer page,
                             @RequestParam(value = "size", defaultValue = "10") Integer size,
                             Map<String, Object> map) {
        PageRequest request = new PageRequest(page - 1, size);
        Page<BuyerComplainForm> buyerComplainFormPage = buyerComplainService.getAll(request);
        map.put("buyerComplainPage", buyerComplainFormPage);
        map.put("currentPage", page);
        map.put("size", size);
        return new ModelAndView("support/complain/list", map);
    }

    @GetMapping("/index")
    public ModelAndView index(@RequestParam(value = "id") String id,
                              Map<String, Object> map) {
        if (!StringUtils.isEmpty(id)) {
            BuyerComplain buyerComplain = buyerComplainService.findOne(id);
            map.put("buyerComplain", buyerComplain);
        }

        return new ModelAndView("support/complain/index", map);
    }

    @PostMapping("/reply")
    public ModelAndView reply(@Valid BuyerComplainForm buyerComplainForm, BindingResult bindingResult, Map<String, Object> map) {
        if (bindingResult.hasErrors()) {
            map.put("msg", bindingResult.getFieldError().getDefaultMessage());
            map.put("url", "/sell/support/buyerComplain/list");
            return new ModelAndView("common/error", map);
        }
        BuyerComplain buyerComplain = buyerComplainService.findOne(buyerComplainForm.getId());
        try {
            if (buyerComplain == null) {
                throw new SellException(ResultEnum.BUYER_COMPLAIN_NOT_EXIST);
            }
            if (StringUtils.isEmpty(buyerComplainForm.getSupportReply())){
                map.put("msg", "support回复为空");
                map.put("url", "/sell/support/buyerComplain/list");
                return new ModelAndView("common/error", map);
            }
        }catch (SellException se){
            map.put("msg", se.getMessage());
            map.put("url", "/sell/support/buyerComplain/list");
            return new ModelAndView("common/error", map);
        }
        //BeanUtils.copyProperties(buyerComplainForm,buyerComplain);
        buyerComplain.setSupportReply(buyerComplainForm.getSupportReply());
        buyerComplain.setState(BuyerComplainEnum.SUPPORT_REPLY.getStatus());
        buyerComplainService.save(buyerComplain);
        map.put("url", "/sell/support/buyerComplain/list");
        return new ModelAndView("common/success", map);
    }
}
