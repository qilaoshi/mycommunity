package com.example.community.community.controller;

import com.example.community.community.dto.UploadDto;
import com.example.community.community.provider.UcloudProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Controller
public class UploadController {
    @Autowired
    private UcloudProvider ucloudProvider;

    @RequestMapping("/file/upload")
    @ResponseBody
    public Object uploadImg(HttpServletRequest request){
        MultipartHttpServletRequest multipartHttpServletRequest=(MultipartHttpServletRequest)request;
        MultipartFile file=multipartHttpServletRequest.getFile("editormd-image-file");
        System.out.println(file);
        try {
            String filename= ucloudProvider.upload(file.getInputStream(),file.getContentType(),file.getOriginalFilename());
            UploadDto uploadDto=new  UploadDto();
            uploadDto.setUrl(filename);
            uploadDto.setSuccess(1);
            return uploadDto;
        } catch (IOException e) {
            e.printStackTrace();
            UploadDto uploadDto=new  UploadDto();
            uploadDto.setSuccess(0);
            uploadDto.setMessage("上传失败");
            return uploadDto;
        }
    }
}
