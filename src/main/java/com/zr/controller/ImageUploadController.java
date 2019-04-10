package com.zr.controller;

import com.UpYun;
import com.upyun.UpException;
import com.zr.config.UpYunConfig;
import com.zr.utils.ResultVoUtil;
import com.zr.vo.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @Auther: Administrator (zhangrun macmanboy@foxmail.com)
 * @Date: 2019/3/27 10:32
 * @Description:
 */
@RestController
@RequestMapping("/image")
public class ImageUploadController {

    @Autowired
    private UpYunConfig upYunConfig;

    @PostMapping("/upload")
    public ResultVo upload(@RequestParam("file_data") MultipartFile multipartFile) throws IOException, UpException {
        UpYun upyun = new UpYun(upYunConfig.getBucketName(), upYunConfig.getUsername(), upYunConfig.getPassword());
        String fileName = String.format("%s.%s", UUID.randomUUID().toString(), multipartFile.getOriginalFilename().substring(multipartFile.getOriginalFilename().lastIndexOf(".") + 1));
        upyun.writeFile(fileName, multipartFile.getInputStream(), true, new HashMap<>());

        Map map = new HashMap<>();
        map.put("fileName", fileName);
        return ResultVoUtil.success(map);
    }
}
