package com.zr.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * @Auther: Administrator (zhangrun macmanboy@foxmail.com)
 * @Date: 2019/3/20 22:11
 * @Description:
 */
public class FastJsonUtil {
    public static String objectToJson(Object object) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setPrettyPrinting();
        Gson gson = gsonBuilder.create();
        return gson.toJson(object);
    }
}
