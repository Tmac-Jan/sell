package com.zr.service.impl;

import com.zr.dataobject.OrderDetail;
import com.zr.dataobject.ProductInfo;
import com.zr.dto.OrderDTO;
import com.zr.enums.OrderStatusEnum;
import com.zr.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.awt.print.Pageable;
import java.math.BigDecimal;
import java.util.Arrays;

import static org.junit.Assert.*;

/**
 * @Auther: Administrator (zhangrun macmanboy@foxmail.com)
 * @Date: 2019/3/19 16:56
 * @Description:
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class OrderServiceImplTest {
    @Autowired
    private OrderServiceImpl orderService;

    @Autowired
    private ProductService productService;

    private final String SHOP_ID = "201931901";

    @Test
    public void create() {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setBuyerAddress("北理珠");
        orderDTO.setBuyerName("zhangsan");
        orderDTO.setBuyerPhone("13192266960");
        orderDTO.setBuyerOpenid("oG-g11sug-qZ8EDj8JAThcB6gi5Y");
        orderDTO.setShopId("201931901");
        orderDTO.setOrderAmount(new BigDecimal(23.2));
        orderDTO.setOrderStatus(OrderStatusEnum.NEW.getStatus());
        ProductInfo productInfo = productService.findOne("124");
        OrderDetail detail = new OrderDetail();
        BeanUtils.copyProperties(productInfo, detail);
        orderDTO.setOrderDetailList(Arrays.asList(new OrderDetail[]{detail}));
        orderService.create(orderDTO);
    }

    @Test
    public void findOne() {
    }

    @Test
    public void findList() {
    }

    @Test
    public void cancel() {
    }

    @Test
    public void finish() {
    }

    @Test
    public void paid() {
    }

    @Test
    public void findList1() {
    }

    @Test
    public void findByShopId() {
        PageRequest request = new PageRequest(0, 2);
        Page<OrderDTO> orderDTOPage = orderService.findByShopId(SHOP_ID, request);
        Assert.assertTrue("查询所有的订单列表", orderDTOPage.getTotalElements() > 0);
    }
}