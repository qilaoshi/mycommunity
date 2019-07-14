package com.example.community.community.controller;

import com.example.community.community.dto.UploadDto;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class UploadController {

    @GetMapping("/upload")
    @ResponseBody
    public Object uploadImg(@RequestParam(value = "editormd-image-file")MultipartFile multipartFile){
        System.out.println(multipartFile+"图片路劲");
        UploadDto uploadDto=new  UploadDto();
        uploadDto.setMessage("成功");
        uploadDto.setUrl("/image/iphoneX.jpg");
        uploadDto.setSuccess(1);
        return null;
    }
}
