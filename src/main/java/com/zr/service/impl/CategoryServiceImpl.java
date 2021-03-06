package com.zr.service.impl;

import com.zr.dataobject.ProductCategory;
import com.zr.repository.ProductCategoryRepository;
import com.zr.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

/**
 * @Auther: Administrator (zhangrun macmanboy@foxmail.com)
 * @Date: 2019/3/13 10:38
 * @Description:
 * 商家类目
 */
@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private ProductCategoryRepository repository;

    @Override
    public ProductCategory findOne(Integer categoryId) {
        return repository.findOne(categoryId);
    }

    @Override
    public List<ProductCategory> findAll() {
        return repository.findAll();
    }

    @Override
    public List<ProductCategory> findByCategoryTypeIn(List<String> categoryTypeList) {
        return repository.findByCategoryTypeIn(categoryTypeList);
    }

    @Override
    public List<ProductCategory> findByShopId(String shopId) {
        return repository.findByShopId(shopId);
    }

    @Override
    public ProductCategory save(ProductCategory productCategory) {
        //生成UUID，该UUID与商铺对应，商品与此UUID对应
        String uuidCategoryType = UUID.randomUUID().toString();
        productCategory.setCategoryType(uuidCategoryType);
        return repository.save(productCategory);
    }
}
