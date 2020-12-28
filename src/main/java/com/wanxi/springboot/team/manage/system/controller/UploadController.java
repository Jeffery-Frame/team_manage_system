package com.wanxi.springboot.team.manage.system.controller;

import com.wanxi.springboot.team.manage.system.api.CommonResult;
import com.wanxi.springboot.team.manage.system.util.Upload;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class UploadController {
//    @Value("${upload.basePath}")
//    public String basePath;
//    @Value("${upload.outPath}")
//    public String outPath;
//    @Value("${upload.url}")
//    public String url;
    @PostMapping("/upload")
    public CommonResult upload(HttpServletRequest req){
//        return Upload.fileUpload(req,basePath,outPath,url);
        return CommonResult.success("success");
    }
}
