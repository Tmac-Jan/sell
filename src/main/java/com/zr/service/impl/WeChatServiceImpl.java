package com.zr.service.impl;

import com.zr.config.WechatAccountConfig;
import com.zr.dataobject.ShopInfo;
import com.zr.dto.OrderDTO;
import com.zr.enums.ResultEnum;
import com.zr.exception.SellException;
import com.zr.service.ShopInfoService;
import com.zr.service.WeChatMessageService;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateData;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * @Auther: Administrator (zhangrun macmanboy@foxmail.com)
 * @Date: 2019/3/22 15:58
 * @Description:
 * 微信公众号消息推送
 */
@Service
@Slf4j
public class WeChatServiceImpl implements WeChatMessageService {
    @Autowired
    private WxMpService wxMpService;
    @Autowired
    private WechatAccountConfig accountConfig;
    @Autowired
    private ShopInfoService shopInfoService;
    @Override
    public void orderStatus(OrderDTO orderDTO) {
        WxMpTemplateMessage templateMessage = new WxMpTemplateMessage();
        templateMessage.setTemplateId(accountConfig.getTemplateId().get("orderStatus"));
        templateMessage.setToUser(orderDTO.getBuyerOpenid());

        ShopInfo shopInfo = shopInfoService.findById(orderDTO.getShopId());
        if (shopInfo==null){
            throw new SellException(ResultEnum.SHOP_NOT_EXIST);
        }
        List<WxMpTemplateData> data = Arrays.asList(
                new WxMpTemplateData("first", "订单信息"+"\r\n","#405F8E"),
                new WxMpTemplateData("key1", shopInfo.getShopName()+"\r\n"),
                new WxMpTemplateData("key2", shopInfo.getShopPhone()+"\r\n"),
                new WxMpTemplateData("key3", orderDTO.getOrderId()+"\r\n","#405F8E"),
                new WxMpTemplateData("key4", orderDTO.getOrderStatusEnum().getMessage()+"\r\n"),
                new WxMpTemplateData("key5", "￥" + orderDTO.getOrderAmount()+"\r\n"),
                new WxMpTemplateData("key6",orderDTO.getBuyerName()+"\r\n" ),
                new WxMpTemplateData("key7", orderDTO.getBuyerPhone()+"\r\n"),
                new WxMpTemplateData("key8", orderDTO.getBuyerAddress()+"\r\n"),
                new WxMpTemplateData("result", "亲，记得五星好评哦！"+"\r\n","#405F8E")
        );
        templateMessage.setData(data);
        try {
            wxMpService.getTemplateMsgService().sendTemplateMsg(templateMessage);
        }catch (WxErrorException e) {
            log.error("【微信模版消息】发送失败, {}", e);
        }
    }
}
