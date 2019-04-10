package com.zr.controller;

import com.zr.converter.AdInfoToAdInfoFormConverter;
import com.zr.dataobject.AdInfo;
import com.zr.exception.NoAdException;
import com.zr.form.AdInfoForm;
import com.zr.service.AdService;
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
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.Map;

/**
 * @Auther: Administrator (zhangrun macmanboy@foxmail.com)
 * @Date: 2019/3/25 13:21
 * @Description:
 */
@Controller
@RequestMapping("/support/ad")
@Slf4j
public class SupportToAdController {
    @Autowired
    private AdService adService;
    @GetMapping("/list")
    public ModelAndView list(@RequestParam(value = "page", defaultValue = "1") Integer page,
                             @RequestParam(value = "size", defaultValue = "10") Integer size,
                             Map<String, Object> map) {
        PageRequest request = new PageRequest(page - 1, size);
        Page<AdInfoForm> adInfoFormPage = adService.getAllToAdInfoForm(request);
        map.put("adInfoFormPage", adInfoFormPage);
        map.put("currentPage", page);
        map.put("size", size);
        return new ModelAndView("support/ad/list", map);
    }

    @GetMapping("/index")
    public ModelAndView index(@RequestParam(value = "id", required = false) String id,
                              Map<String, Object> map) {
        if (!StringUtils.isEmpty(id)) {
            AdInfo adInfo = adService.findOne(id);
            try {
                if (adInfo == null){
                    throw new NoAdException();
                }
            }catch (NoAdException e){//广告不存在
                log.error("【广告不存在异常】发生异常{}", e);
                map.put("msg", e.getMessage());
                map.put("url", "/sell/support/ad/list");
                return new ModelAndView("common/error", map);
            }
          //  OrderEvalForm orderEvalForm = OrderEvalToOrderEvalFormConverter.convert(orderEval);
            AdInfoForm adInfoForm = AdInfoToAdInfoFormConverter.convert(adInfo);
            map.put("adInfoForm", adInfoForm);
        }

        return new ModelAndView("support/ad/index", map);
    }
    @PostMapping("/save")
    public ModelAndView reply(@Valid AdInfoForm adInfoForm, BindingResult bindingResult, Map<String,Object> map){
        System.out.println("Come on!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        if (bindingResult.hasErrors()) {
            map.put("msg", bindingResult.getFieldError().getDefaultMessage());
            map.put("url", "/sell/support/ad/index");
            return new ModelAndView("common/error", map);
        }
        AdInfo adInfo = new AdInfo();
        try {
            if (!StringUtils.isEmpty(adInfoForm.getId())){
                adInfo = adService.findOne(adInfoForm.getId());
                if (adInfo == null){
                    throw  new NoAdException();
                }
            }else{
                adInfoForm.setId(RandomUtil.genUniqueKey());
            }
            BeanUtils.copyProperties(adInfoForm,adInfo);
            adService.save(adInfo);
        }catch (NoAdException e){
            map.put("msg", "没有此广告异常");
            map.put("url", "/sell/support/ad/index");
            return new ModelAndView("common/error", map);
        }
        map.put("url", "/sell/support/ad/list");
        return new ModelAndView("common/success", map);
    }
}
