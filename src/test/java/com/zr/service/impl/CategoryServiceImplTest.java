package com.zr.service.impl;

import com.zr.dataobject.ProductCategory;
import com.zr.repository.ProductCategoryRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @Auther: Administrator (zhangrun macmanboy@foxmail.com)
 * @Date: 2019/3/13 10:45
 * @Description:
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class CategoryServiceImplTest {

    @Autowired
    private ProductCategoryRepository repository;
    @Test
    public void findOne() throws Exception {
        ProductCategory productCategory = repository.findOne(1);
        System.out.println(productCategory.toString());
    }

    @Test
    public void findAll() throws Exception {
    }

    @Test
    public void findByCategoryTypeIn() throws Exception {

    }

    @Test
    public void findByShopId() throws Exception {
        //List<Integer> list = Arrays.asList(2,3,4);
        List<ProductCategory> result = repository.findByShopId("1");
        //System.out.println(result.toString());
        Assert.assertNotEquals(0, result.size());
    }

    @Test
    public void save() throws Exception {
    }
}