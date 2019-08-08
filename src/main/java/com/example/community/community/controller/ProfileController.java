package com.example.community.community.controller;

import com.example.community.community.Service.LetterService;
import com.example.community.community.Service.NotificationService;
import com.example.community.community.Service.PublishService;
import com.example.community.community.Service.UserService;
import com.example.community.community.dto.CommonJsonDto;
import com.example.community.community.model.*;
import com.example.community.community.provider.UcloudProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Controller
public class ProfileController {
    @Autowired
    private PublishService publishService;
    @Autowired
    private UserService userService;
    @Autowired
    private NotificationService notificationService;
    @Autowired
    private LetterService letterService;
    @Autowired
    private UcloudProvider ucloudProvider;


    @GetMapping("/profile/{action}")
    public String profile(@PathVariable(value = "action") String action,
                          Model model,
                          HttpServletRequest request) {
        User user =  (User)request.getSession().getAttribute("user");
        if ("question".equals(action)) {
            List<UserWithPublish> myPublishList=publishService.selectByUserId(user.getUserId());
            System.out.println("我的提问"+myPublishList.size());
            if(myPublishList.size()!=0){
                model.addAttribute("myPublishList",myPublishList);
            }
            model.addAttribute("section", "question");
            model.addAttribute("sectionName", "我的问题");
        } else if ("replies".equals(action)) {
            List<NotifiWithUserWithPublish> notificationList=userService.selectNotifi(user.getUserId());
            if (notificationList!=null){
            model.addAttribute("nitificationList",notificationList);
            }
            model.addAttribute("section", "replies");
            model.addAttribute("sectionName", "我的回复");
        } else if("myletter".equals(action)){
            List<LetterWithUser> letterList=letterService.selectByUserId(user.getUserId());
            if (letterList.size()!=0&&letterList!=null) {
                for (LetterWithUser letterWithUser : letterList) {
                    if (letterWithUser.getType() == 0) {
                        model.addAttribute("notRead", 0);
                        break;
                    }
                }
                model.addAttribute("letterList", letterList.get(0));
            }
            model.addAttribute("section", "myletter");
            model.addAttribute("sectionName", "我的私信");
        }else if("myMessage".equals(action)){
            User user1=userService.selectByUserId(user.getUserId());
            model.addAttribute("userMessage",user1);
            model.addAttribute("section", "myMessage");
            model.addAttribute("sectionName", "个人信息");
        }
        if (user==null){
            return "redirect:/";
        }
        List<Publish> publishListByCreator=publishService.selectByCreator(3);
        model.addAttribute("publishListByCreator",publishListByCreator);
        return "profile";
    }

    @GetMapping("/updateStatus")
    public Object updateStatus(int nId,int publishId){
        System.out.println("来了"+nId);
        notificationService.updateStatus(nId);
        return "redirect:/question/"+publishId;
    }

    @PostMapping("/updatePicture")
        public Object updatePicture(HttpServletRequest request){
        User user=(User)request.getSession().getAttribute("user");
        MultipartHttpServletRequest multipartHttpServletRequest=(MultipartHttpServletRequest)request;
        MultipartFile file=multipartHttpServletRequest.getFile("uploadFile");
        try {
            String filename= ucloudProvider.upload(file.getInputStream(),file.getContentType(),file.getOriginalFilename());
            user.setAvatarUrl(filename);
            System.out.println("修改图片"+filename);
            userService.updateAvatarUrl(user);
            return "redirect:/profile/myMessage";
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "错误";
    }

    @PostMapping("/updateEmail")
    @ResponseBody
    public Object updateEmail(@RequestBody User users,HttpServletRequest request,Model model){
        System.out.println(users.getEmail());
        User user=(User)request.getSession().getAttribute("user");
        userService.updateEmail(users.getEmail(),user.getUserId());
        CommonJsonDto commonJsonDto=new CommonJsonDto();
        commonJsonDto.setCode(0);
        commonJsonDto.setMsg("修改成功");
        return commonJsonDto;
    }

    @PostMapping("/updatePassword")
    @ResponseBody
    public Object updatePassword(@RequestBody User users,HttpServletRequest request,Model model){
        User user=(User)request.getSession().getAttribute("user");
        userService.updatePassword(users.getPassword(),user.getUserId());
        CommonJsonDto commonJsonDto=new CommonJsonDto();
        commonJsonDto.setCode(0);
        commonJsonDto.setMsg("修改成功");
        return commonJsonDto;
    }

    @PostMapping("/updateName")
    @ResponseBody
    public Object updateName(@RequestBody User users,HttpServletRequest request){
        CommonJsonDto commonJsonDto=new CommonJsonDto();
        User user=(User)request.getSession().getAttribute("user");
        userService.updateName(users.getUsername(),user.getUserId());
        commonJsonDto.setMsg("修改成功");
        commonJsonDto.setCode(0);
        return commonJsonDto;
    }

}
