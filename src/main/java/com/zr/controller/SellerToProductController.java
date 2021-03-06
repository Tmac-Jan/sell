package com.zr.controller;

import com.zr.dataobject.ProductCategory;
import com.zr.dataobject.ProductInfo;
import com.zr.dataobject.SellerInfo;
import com.zr.dataobject.ShopInfo;
import com.zr.exception.SellException;
import com.zr.form.ProductForm;
import com.zr.service.CategoryService;
import com.zr.service.ProductService;
import com.zr.service.SellerInfoService;
import com.zr.service.ShopInfoService;
import com.zr.utils.GetTokenValueUtil;
import com.zr.utils.RandomUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;
import sun.security.util.KeyUtil;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;
import java.util.Map;

/**
 * @Auther: Administrator (zhangrun macmanboy@foxmail.com)
 * @Date: 2019/3/20 15:06
 * @Description:
 */
@Controller
@RequestMapping("/seller/product")
public class SellerToProductController {
    @Autowired
    private ProductService productService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private SellerInfoService sellerInfoService;
    @Autowired
    private ShopInfoService shopInfoService;
    /**
     * 列表
     * @param page
     * @param size
     * @param map
     * @return
     */
    @GetMapping("/list")
    public ModelAndView list(@RequestParam(value = "page", defaultValue = "1") Integer page,
                             @RequestParam(value = "size", defaultValue = "10") Integer size,
                             Map<String, Object> map) {
        PageRequest request = new PageRequest(page - 1, size);
        HttpServletRequest httpServletRequest = ((ServletRequestAttributes) RequestContextHolder
                .getRequestAttributes()).getRequest();
        String sellerOpenid = GetTokenValueUtil.getToken(httpServletRequest);
        SellerInfo sellerInfo = sellerInfoService.findSellerInfoByOpenid(sellerOpenid);
        ShopInfo shopInfo = shopInfoService.findBySellerInfo(sellerInfo);
        Page<ProductInfo> productInfoPage = productService.findAllByShopId(shopInfo.getId(),request);
        map.put("productInfoPage", productInfoPage);
        map.put("currentPage", page);
        map.put("size", size);
        return new ModelAndView("product/list", map);
    }

    /**
     * 商品上架
     * @param productId
     * @param map
     * @return
     */
    @RequestMapping("/on_sale")
    public ModelAndView onSale(@RequestParam("productId") String productId,
                               Map<String, Object> map) {
        try {
            productService.onSale(productId);
        } catch (SellException e) {
            map.put("msg", e.getMessage());
            map.put("url", "/sell/seller/product/list");
            return new ModelAndView("common/error", map);
        }

        map.put("url", "/sell/seller/product/list");
        return new ModelAndView("common/success", map);
    }
    /**
     * 商品下架
     * @param productId
     * @param map
     * @return
     */
    @RequestMapping("/off_sale")
    public ModelAndView offSale(@RequestParam("productId") String productId,
                                Map<String, Object> map) {
        try {
            productService.offSale(productId);
        } catch (SellException e) {
            map.put("msg", e.getMessage());
            map.put("url", "/sell/seller/product/list");
            return new ModelAndView("common/error", map);
        }

        map.put("url", "/sell/seller/product/list");
        return new ModelAndView("common/success", map);
    }

    @GetMapping("/index")
    public ModelAndView index(@RequestParam(value = "productId", required = false) String productId,
                              Map<String, Object> map) {
        if (!StringUtils.isEmpty(productId)) {
            ProductInfo productInfo = productService.findOne(productId);
            System.out.println("productInfo:"+productInfo);
            map.put("productInfo", productInfo);
        }

        //PageRequest request = new PageRequest(page - 1, size);
        HttpServletRequest httpServletRequest = ((ServletRequestAttributes) RequestContextHolder
                .getRequestAttributes()).getRequest();
        String sellerOpenid = GetTokenValueUtil.getToken(httpServletRequest);
        SellerInfo sellerInfo = sellerInfoService.findSellerInfoByOpenid(sellerOpenid);
        ShopInfo shopInfo = shopInfoService.findBySellerInfo(sellerInfo);
        //查询所有的类目
        List<ProductCategory> categoryList = categoryService.findByShopId(shopInfo.getId());
        map.put("categoryList", categoryList);

        return new ModelAndView("product/index", map);
    }

    /**
     * 保存/更新
     * @param form
     * @param bindingResult
     * @param map
     * @return
     */
    @PostMapping("/save")
//    @Cacheable(cacheNames = "product", key = "123")
//    @Cacheable(cacheNames = "product", key = "456")
//    @CachePut(cacheNames = "product", key = "123")
    @CacheEvict(cacheNames = "product", allEntries = true, beforeInvocation = true)
    public ModelAndView save(@Valid ProductForm form,
                             BindingResult bindingResult,
                             Map<String, Object> map) {
        if (bindingResult.hasErrors()) {
            map.put("msg", bindingResult.getFieldError().getDefaultMessage());
            map.put("url", "/sell/seller/product/index");
            return new ModelAndView("common/error", map);
        }

        ProductInfo productInfo = new ProductInfo();
        try {
            //如果productId为空, 说明是新增
            if (!StringUtils.isEmpty(form.getProductId())) {
                productInfo = productService.findOne(form.getProductId());
            } else {
                form.setProductId(RandomUtil.genUniqueKey());
            }
            BeanUtils.copyProperties(form, productInfo);
            productService.save(productInfo);
        } catch (SellException e) {
            map.put("msg", e.getMessage());
            map.put("url", "/sell/seller/product/index");
            return new ModelAndView("common/error", map);
        }

        map.put("url", "/sell/seller/product/list");
        return new ModelAndView("common/success", map);
    }
}
