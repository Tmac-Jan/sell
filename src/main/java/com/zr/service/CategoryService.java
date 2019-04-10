package com.zr.service;

import com.zr.dataobject.ProductCategory;

import java.util.List;

/**
 * @Auther: Administrator (zhangrun macmanboy@foxmail.com)
 * @Date: 2019/3/13 10:33
 * @Description:
 */
public interface CategoryService {
    ProductCategory findOne(Integer categoryId);

    List<ProductCategory> findAll();

    List<ProductCategory> findByCategoryTypeIn(List<String> categoryTypeList);

    List<ProductCategory> findByShopId(String shopId);

    ProductCategory save(ProductCategory productCategory);
}
