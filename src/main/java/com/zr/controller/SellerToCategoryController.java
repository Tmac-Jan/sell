package com.zr.controller;

import com.zr.dataobject.ProductCategory;
import com.zr.dataobject.SellerInfo;
import com.zr.dataobject.ShopInfo;
import com.zr.exception.SellException;
import com.zr.form.CategoryForm;
import com.zr.service.CategoryService;
import com.zr.service.SellerInfoService;
import com.zr.service.ShopInfoService;
import com.zr.utils.GetTokenValueUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
import java.util.List;
import java.util.Map;

/**
 * @Auther: Administrator (zhangrun macmanboy@foxmail.com)
 * @Date: 2019/3/20 15:05
 * @Description:
 */
@Controller
@RequestMapping("/seller/category")
public class SellerToCategoryController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private SellerInfoService sellerInfoService;
    @Autowired
    private ShopInfoService shopInfoService;
    /**
     * 类目列表
     * @param map
     * @return
     */
    @GetMapping("/list")
    public ModelAndView list(Map<String, Object> map) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
                .getRequestAttributes()).getRequest();
        String sellerOpenid = GetTokenValueUtil.getToken(request);
        SellerInfo sellerInfo = sellerInfoService.findSellerInfoByOpenid(sellerOpenid);
        ShopInfo shopInfo = shopInfoService.findBySellerInfo(sellerInfo);
        List<ProductCategory> categoryList = categoryService.findByShopId(shopInfo.getId());
        map.put("categoryList", categoryList);
        return new ModelAndView("category/list", map);
    }

    /**
     * 展示
     * @param categoryId
     * @param map
     * @return
     */
    @GetMapping("/index")
    public ModelAndView index(@RequestParam(value = "categoryId", required = false) Integer categoryId,
                              Map<String, Object> map) {
        if (categoryId != null) {
            ProductCategory productCategory = categoryService.findOne(categoryId);
            map.put("category", productCategory);
        }

        return new ModelAndView("category/index", map);
    }

    /**
     * 保存/更新
     * @param form
     * @param bindingResult
     * @param map
     * @return
     */
    @PostMapping("/save")
    public ModelAndView save(@Valid CategoryForm form,
                             BindingResult bindingResult,
                             Map<String, Object> map) {
        if (bindingResult.hasErrors()) {
            map.put("msg", bindingResult.getFieldError().getDefaultMessage());
            map.put("url", "/sell/seller/category/index");
            return new ModelAndView("common/error", map);
        }
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
                .getRequestAttributes()).getRequest();
        String sellerOpenid = GetTokenValueUtil.getToken(request);
        SellerInfo sellerInfo = sellerInfoService.findSellerInfoByOpenid(sellerOpenid);
        ShopInfo shopInfo = shopInfoService.findBySellerInfo(sellerInfo);
        ProductCategory productCategory = new ProductCategory();
        try {
            if (form.getCategoryId() != null) {
                productCategory = categoryService.findOne(form.getCategoryId());
            }

            BeanUtils.copyProperties(form, productCategory);
            productCategory.setShopId(shopInfo.getId());
            categoryService.save(productCategory);
        } catch (SellException e) {
            map.put("msg", e.getMessage());
            map.put("url", "/sell/seller/category/index");
            return new ModelAndView("common/error", map);
        }

        map.put("url", "/sell/seller/category/list");
        return new ModelAndView("common/success", map);
    }
}
