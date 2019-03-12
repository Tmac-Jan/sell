package com.zr.service.impl;

import com.zr.dataobject.BuyerAddress;
import com.zr.dataobject.BuyerInfo;
import com.zr.repository.BuyerAddressRepository;
import com.zr.repository.BuyerInfoRepository;
import com.zr.service.BuyerAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Auther: Administrator (zhangrun macmanboy@foxmail.com)
 * @Date: 2019/3/12 16:30
 * @Description:
 */
@Service
public class BuyerAddressServiceImpl implements BuyerAddressService {

    @Autowired
    private BuyerAddressRepository buyerAddressRepository;
    @Override
    public List<BuyerAddress> findBuyerAddressByBuyerInfo(BuyerInfo buyerInfo) {
        return buyerAddressRepository.findByBuyerInfo(buyerInfo);
    }
}
