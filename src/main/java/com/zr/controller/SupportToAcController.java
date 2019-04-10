package com.zr.controller;

import com.zr.converter.AcInfoToAcInfoFormConverter;
import com.zr.dataobject.AcInfo;
import com.zr.dataobject.AdInfo;
import com.zr.exception.NoAcException;
import com.zr.exception.NoAdException;
import com.zr.form.AcInfoForm;
import com.zr.service.AcService;
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
 * @Date: 2019/3/28 11:09
 * @Description:
 */
@Controller
@RequestMapping("/support/ac")
@Slf4j
public class SupportToAcController {
    @Autowired
    private AcService acService;
    @GetMapping("/list")
    public ModelAndView list(@RequestParam(value = "page", defaultValue = "1") Integer page,
                             @RequestParam(value = "size", defaultValue = "10") Integer size,
                             Map<String, Object> map) {
        PageRequest request = new PageRequest(page - 1, size);
        Page<AcInfoForm> acInfoFormPage = acService.getAllToAcInfoForm(request);
        map.put("acInfoFormPage", acInfoFormPage);
        map.put("currentPage", page);
        map.put("size", size);
        return new ModelAndView("support/ac/list", map);
    }

    @GetMapping("/index")
    public ModelAndView index(@RequestParam(value = "id", required = false) String id,
                              Map<String, Object> map) {
        if (!StringUtils.isEmpty(id)) {
            AcInfo acInfo = acService.findOne(id);
            try {
                if (acInfo == null){
                    throw new NoAdException();
                }
            }catch (NoAdException e){//活动不存在
                log.error("【活动不存在异常】发生异常{}", e);
                map.put("msg", e.getMessage());
                map.put("url", "/sell/support/ac/list");
                return new ModelAndView("common/error", map);
            }
            AcInfoForm acInfoForm = AcInfoToAcInfoFormConverter.convert(acInfo);
            map.put("acInfoForm", acInfoForm);
        }

        return new ModelAndView("support/ac/index", map);
    }
    @PostMapping("/save")
    public ModelAndView reply(@Valid AcInfoForm acInfoForm, BindingResult bindingResult, Map<String,Object> map){
        if (bindingResult.hasErrors()) {
            map.put("msg", bindingResult.getFieldError().getDefaultMessage());
            map.put("url", "/sell/support/ac/index");
            return new ModelAndView("common/error", map);
        }
        AcInfo acInfo = new AcInfo();
        try {
            if (!StringUtils.isEmpty(acInfoForm.getId())){
                acInfo = acService.findOne(acInfoForm.getId());
                if (acInfo == null){
                    throw  new NoAcException();
                }
            }else{
                acInfoForm.setId(RandomUtil.genUniqueKey());
            }
            BeanUtils.copyProperties(acInfoForm,acInfo);
            acService.save(acInfo);
        }catch (NoAdException e){
            map.put("msg", "没有此活动异常");
            map.put("url", "/sell/support/ac/index");
            return new ModelAndView("common/error", map);
        }
        map.put("url", "/sell/support/ac/list");
        return new ModelAndView("common/success", map);
    }
}
