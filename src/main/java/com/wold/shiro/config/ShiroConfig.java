package com.wold.shiro.config;

import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {
    //shiroFilterFacrotyBean
    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("getDefaultWebSecurityManager")DefaultWebSecurityManager manager){

        ShiroFilterFactoryBean shiroFilterFactoryBean=new ShiroFilterFactoryBean();
        //设置安全管理器
        /*
            anon：无需认证就可以访问
            authc: 必须认证才能访问
            user:必须拥有 记住我（Remnber me）功能才能使用
            perms:拥有对某个资源的权限才能访问
            role:拥有某个角色权限才能访问
         */

        //拦截
        Map<String,String> filterMap=new LinkedHashMap<>();

        //授权
        filterMap.put("/user/add","perms[user:add]");


        filterMap.put("/user/update","authc");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterMap);

        //设置登录请求
        shiroFilterFactoryBean.setLoginUrl("/toLogin");

        //设置未授权页面
        shiroFilterFactoryBean.setUnauthorizedUrl("/noauth ");
        shiroFilterFactoryBean.setSecurityManager(manager);
        return shiroFilterFactoryBean;
    }
    //DefaulWebSecurutyManager
    @Bean
    public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("userRealm") UserRealm userRealm){
        DefaultWebSecurityManager defaultWebSecurityManager=new DefaultWebSecurityManager();
        //关联Realm
        defaultWebSecurityManager.setRealm(userRealm);

        return defaultWebSecurityManager;
    }

    //创建Realm对象，需要自定义
    @Bean
    public UserRealm userRealm(){
        UserRealm realm=new UserRealm();

        //创建一个密码验证算法捕获器
        HashedCredentialsMatcher matcher=new HashedCredentialsMatcher();
        //设置密码验证为MD5加密验证
        matcher.setHashAlgorithmName("MD5");
        //设置加密次数为1次
        matcher.setHashIterations(1);
        //配置加密验证控制器
        realm.setCredentialsMatcher(matcher);

        return realm;
    }
}
