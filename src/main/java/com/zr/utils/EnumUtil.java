package com.zr.utils;

import com.zr.enums.StatusEnum;


public class EnumUtil {

    public static <T extends StatusEnum> T getByCode(Integer code, Class<T> enumClass) {
        for (T each: enumClass.getEnumConstants()) {
            if (code.equals(each.getStatus())) {
                return each;
            }
        }
        return null;
    }
}
