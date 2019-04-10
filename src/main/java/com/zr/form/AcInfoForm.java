package com.zr.form;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.zr.utils.serializer.Date2LongSerializer;
import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.Id;
import java.util.Date;

/**
 * @Auther: Administrator (zhangrun macmanboy@foxmail.com)
 * @Date: 2019/3/28 11:24
 * @Description:
 */
@Data
public class AcInfoForm {

    private String id;
    @NotEmpty(message = "活动名字必填")
    private String acName;
    @NotEmpty(message = "活动图片必填")
    private String acImage;
    private String modifierOpenid;
    private String modifierName;
    @JsonSerialize(using = Date2LongSerializer.class)
    private Date createTime;
    @JsonSerialize(using = Date2LongSerializer.class)
    private Date updateTime;
}
