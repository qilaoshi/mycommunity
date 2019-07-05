package com.example.community.community.controller;

import com.example.community.community.dto.AccessTokenAto;
import com.example.community.community.dto.GithubUser;
import com.example.community.community.provider.GithubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthorizeController {
    @Autowired
    GithubProvider githubProvider;
    @Value("${github.Client.id}")
    private String clientId;
    @Value("${github.Redirect.uri}")
    private String redirectUri;
    @Value("${github.setClient.secret}")
    private String clientSecret;

    @GetMapping("/callback")
    public String callback(@RequestParam(name="code")String code,
                           @RequestParam(name="state")String state){
        AccessTokenAto accessTokenAto=new AccessTokenAto();
        accessTokenAto.setCode(code);
        accessTokenAto.setState(state);
        accessTokenAto.setClient_id(clientId);
        accessTokenAto.setRedirect_uri(redirectUri);
        accessTokenAto.setClient_secret(clientSecret);
        String accesstoken=githubProvider.getAccessToker(accessTokenAto);
        GithubUser githubUser=githubProvider.githubUser(accesstoken);
        return "index";
    }
}
