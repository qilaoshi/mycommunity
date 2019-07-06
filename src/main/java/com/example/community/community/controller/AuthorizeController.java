package com.example.community.community.controller;

import com.example.community.community.Service.UserService;
import com.example.community.community.dto.AccessTokenAto;
import com.example.community.community.dto.GithubUser;
import com.example.community.community.model.User;
import com.example.community.community.provider.GithubProvider;
import com.sun.deploy.net.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

@Controller
public class AuthorizeController {
    @Autowired
    GithubProvider githubProvider;
    @Autowired
    UserService userService;
    @Value("${github.Client.id}")
    private String clientId;
    @Value("${github.Redirect.uri}")
    private String redirectUri;
    @Value("${github.setClient.secret}")
    private String clientSecret;

    @GetMapping("/callback")
    public String callback(@RequestParam(name="code")String code,
                           @RequestParam(name="state")String state,
                           HttpServletResponse response,
                           HttpServletRequest request){
        AccessTokenAto accessTokenAto=new AccessTokenAto();
        accessTokenAto.setCode(code);
        accessTokenAto.setState(state);
        accessTokenAto.setClient_id(clientId);
        accessTokenAto.setRedirect_uri(redirectUri);
        accessTokenAto.setClient_secret(clientSecret);
        String accesstoken=githubProvider.getAccessToker(accessTokenAto);
        GithubUser githubUser=githubProvider.githubUser(accesstoken);
        if (githubUser!=null){
            User user=new User();
            String token=UUID.randomUUID().toString();
            user.setUsername(githubUser.getName());
            user.setAccountId(String.valueOf(githubUser.getId()));
            user.setToken(token);
            user.setGmtCreate(System.currentTimeMillis());
            user.setGmtModified(user.getGmtCreate());
            user.setAvatarUrl(githubUser.getAvatar_url());
            userService.insert(user);
            response.addCookie(new Cookie("token",token));
            return "redirect:/";
        }else {
            return "redirect:/";
        }
    }
}

