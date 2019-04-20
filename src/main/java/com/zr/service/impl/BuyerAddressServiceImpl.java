package com.zr.service.impl;

import com.zr.converter.BuyerAddressToBuyerAddressFormConverter;
import com.zr.dataobject.BuyerAddress;
import com.zr.dataobject.BuyerInfo;
import com.zr.form.BuyerAddressForm;
import com.zr.repository.BuyerAddressRepository;
import com.zr.repository.BuyerInfoRepository;
import com.zr.service.BuyerAddressService;
import com.zr.utils.RandomUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

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
    public Page<BuyerAddressForm> findBuyerAddressByBuyerInfo(BuyerInfo buyerInfo,Pageable pageable) {
        Page<BuyerAddress> buyerAddressPage = buyerAddressRepository.findByBuyerInfo(buyerInfo,pageable);
        List<BuyerAddressForm> buyerAddressFormList = BuyerAddressToBuyerAddressFormConverter.convert(buyerAddressPage.getContent());
        return new PageImpl<>(buyerAddressFormList,pageable,buyerAddressPage.getTotalElements());
    }

    @Override
    public BuyerAddress findBuyerAddressByIdAndOpenid(String id, String openId) {
        return buyerAddressRepository.findByIdAndOpenid(id,openId);
    }

    @Override
    public BuyerAddress save(BuyerAddress buyerAddress) {
        if (StringUtils.isEmpty(buyerAddress.getId()))
            buyerAddress.setId(RandomUtil.genUniqueKey());
        return buyerAddressRepository.save(buyerAddress);
    }

    @Override
    public void delete(String id) {
        buyerAddressRepository.delete(id);
    }

    @Override
    public Page<BuyerAddressForm> findAll(Pageable pageable) {
        Page<BuyerAddress> buyerAddressPage = buyerAddressRepository.findAll(pageable);
        List<BuyerAddressForm> buyerAddressFormList = BuyerAddressToBuyerAddressFormConverter.convert(buyerAddressPage.getContent());
        return new PageImpl<>(buyerAddressFormList,pageable,buyerAddressPage.getTotalElements());
    }

    @Override
    public List<BuyerAddressForm> findAllByBuyerInfo(BuyerInfo buyerInfo) {
        List<BuyerAddress> buyerAddressList = buyerAddressRepository.findAllByBuyerInfo(buyerInfo);
        List<BuyerAddressForm> buyerAddressFormList = BuyerAddressToBuyerAddressFormConverter.convert(buyerAddressList);
        return buyerAddressFormList;
    }
}
