package com.example.community.community.config;

import com.example.community.community.Service.UserService;
import com.example.community.community.model.User;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
public class UserRealm extends AuthorizingRealm {
    @Autowired
    private UserService userService;

    /**
     * 执行授权逻辑
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection arg0) {
        System.out.println("执行授权逻辑");
        SimpleAuthorizationInfo info=new SimpleAuthorizationInfo();
        info.addStringPermission("user:add");
        return info;
    }

    /**
     * 执行认证逻辑
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken arg0) throws AuthenticationException {
        System.out.println("执行认证逻辑");
        UsernamePasswordToken usernamePasswordToken=(UsernamePasswordToken)arg0;
        User user=userService.selectUser(usernamePasswordToken.getUsername());
        System.out.println(user.getUsername()+user.getPassword());
        if (!usernamePasswordToken.getUsername().equals(user.getUsername())){
            System.out.println("执行验证用户名前");
            return null;
        }
        System.out.println("执行验证用户名后");
        return new SimpleAuthenticationInfo("",user.getPassword(),"");
    }

}