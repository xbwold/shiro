package com.wold.shiro.config;

import com.wold.shiro.pojo.User;
import com.wold.shiro.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

//自定义userRealm  继承AuthorizingRealm
public class UserRealm extends AuthorizingRealm {

    @Autowired
    private UserService service;
    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("执行了->授权");
        SimpleAuthorizationInfo info=new SimpleAuthorizationInfo();
        info.addStringPermission("user:add");

        //获取当前登录用户信息
        Subject subject=SecurityUtils.getSubject();
        User user=(User)subject.getPrincipal();

        return info ;
    }

    //认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("执行了->认证");

        UsernamePasswordToken userToken=(UsernamePasswordToken) authenticationToken;

        User user=service.getUserByName(userToken.getUsername());

        if(user==null){//认证用户是否存在
            return null;
        }

        //可以加密  在配置类中实现
        //密码认证,shiro完成
        return new SimpleAuthenticationInfo(user,user.getPwd(),"");
    }
}
