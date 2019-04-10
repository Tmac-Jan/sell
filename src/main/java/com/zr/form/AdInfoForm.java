package com.zr.form;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.zr.utils.serializer.Date2LongSerializer;
import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

import java.util.Date;

/**
 * @Auther: Administrator (zhangrun macmanboy@foxmail.com)
 * @Date: 2019/3/28 11:24
 * @Description:
 */
@Data
public class AdInfoForm {
    private String id;

    @NotEmpty(message = "广告名字必填")
    private String adName;

    @NotEmpty(message = "广告图片必填")
    private String adImage;

    private String modifierOpenid;
    private String modifierName;
    @JsonSerialize(using = Date2LongSerializer.class)
    private Date createTime;
    @JsonSerialize(using = Date2LongSerializer.class)
    private Date updateTime;
}
