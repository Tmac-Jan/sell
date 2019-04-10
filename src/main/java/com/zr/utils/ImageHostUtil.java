package com.zr.utils;

import com.zr.dataobject.AdInfo;

/**
 * @Auther: Administrator (zhangrun macmanboy@foxmail.com)
 * @Date: 2019/3/28 17:08
 * @Description:
 */
public class ImageHostUtil {
    public static  String addImageHost(String host,String imgUrl) {
        if (imgUrl.startsWith("//") || imgUrl.startsWith("http")) {
            return imgUrl;
        }

        if (!host.startsWith("http")) {
            host = "//" + host;
        }
        if (!host.endsWith("/")) {
            host = host + "/";
        }
        imgUrl = host + imgUrl;
        return imgUrl;
    }
}
