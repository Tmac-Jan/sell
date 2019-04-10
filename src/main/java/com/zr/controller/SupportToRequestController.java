package com.zr.controller;

import com.zr.converter.ShopRequestFormConverter;
import com.zr.dataobject.ShopRequest;
import com.zr.enums.ResultEnum;
import com.zr.enums.ShopRequestResultEnum;
import com.zr.exception.SellException;
import com.zr.form.ShopRequestForm;
import com.zr.service.ShopRequestService;
import com.zr.utils.EnumUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
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
import java.util.List;
import java.util.Map;

import static com.zr.enums.ShopRequestResultEnum.REQUEST_AGREE;

/**
 * @Auther: Administrator (zhangrun macmanboy@foxmail.com)
 * @Date: 2019/3/28 22:24
 * @Description:
 */
@Controller
@RequestMapping("/support/request")
@Slf4j
public class SupportToRequestController {
    @Autowired
    ShopRequestService shopRequestService;
    @GetMapping("/list")
    public ModelAndView list(@RequestParam(value = "page", defaultValue = "1") Integer page,
                             @RequestParam(value = "size", defaultValue = "10") Integer size,
                             Map<String, Object> map) {
        PageRequest pageRequest = new PageRequest(page - 1, size);
        Page<ShopRequest> shopRequestPage = shopRequestService.findAllOrderByUpdateTimeDesc(pageRequest);
        List<ShopRequestForm> shopRequestFormList = ShopRequestFormConverter.convert(shopRequestPage.getContent());
        Page<ShopRequestForm> shopRequestFormPage = new PageImpl<>(shopRequestFormList,pageRequest,shopRequestPage.getTotalElements());
        map.put("shopRequestFormPage", shopRequestFormPage);
        map.put("currentPage", page);
        map.put("size", size);
        return new ModelAndView("support/request/list", map);
    }

    @GetMapping("/index")
    public ModelAndView index(@RequestParam(value = "id") String id,
                              Map<String, Object> map) {
        if (!StringUtils.isEmpty(id)) {
            ShopRequest shopRequest = shopRequestService.findOne(id);
            try {
                if (shopRequest == null) {
                    throw new SellException(ResultEnum.REQUEST_NOT_EXIST);
                }
            } catch (SellException se) {//申请不存在
                log.error("【Support端申请发生异常】发生异常{}", se);
                map.put("msg", se.getMessage());
                map.put("url", "/sell/support/request/list");
                return new ModelAndView("common/error", map);
            }
            ShopRequestForm shopRequestForm = ShopRequestFormConverter.convert(shopRequest);

            map.put("shopRequestForm", shopRequestForm);
        }
        return new ModelAndView("support/request/index", map);
    }

    @PostMapping("/save")
    public ModelAndView reply(@Valid ShopRequestForm shopRequestForm, BindingResult bindingResult, Map<String, Object> map) {
        if (bindingResult.hasErrors()) {
            map.put("msg", bindingResult.getFieldError().getDefaultMessage());
            map.put("url", "/sell/support/request/list?id="+shopRequestForm.getId());
            return new ModelAndView("common/error", map);
        }
        ShopRequest shopRequest = new ShopRequest();
        try {
            if (!StringUtils.isEmpty(shopRequestForm.getId())) {
                shopRequest = shopRequestService.findOne(shopRequestForm.getId());
                if (shopRequest == null)
                    throw new SellException(ResultEnum.REQUEST_NOT_EXIST);
                //TODO
                BeanUtils.copyProperties(shopRequestForm, shopRequest);
            } else {//申请为新建时
                throw new SellException(ResultEnum.REQUEST_NOT_EXIST);
            }
            ShopRequestResultEnum shopRequestResultEnum = EnumUtil.getByCode(shopRequestForm.getRequestResult(),ShopRequestResultEnum.class);
          switch (shopRequestResultEnum){
              case REQUEST_AGREE:
                  shopRequestService.agreeRequest(shopRequest);
                  break;
              case REQUEST_HANDLE:
                  shopRequestService.handleRequest(shopRequest);
                  break;
              case REQUEST_CANCEL:
                  shopRequestService.cancelRequest(shopRequest);
                  break;
              default:
                      break;
          }
        } catch (SellException se) {
            map.put("msg", se.getMessage());
            map.put("url", "/sell/support/request/list");
            return new ModelAndView("common/error", map);
        }
        //TODO

        map.put("url", "/sell/support/request/list");
        return new ModelAndView("common/success", map);
    }
}
