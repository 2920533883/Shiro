package com.itzhang.config;

import com.itzhang.manager.MyShiroSessionManager;
import com.itzhang.realm.LoginRealm;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.crazycake.shiro.RedisCacheManager;
import org.crazycake.shiro.RedisManager;
import org.crazycake.shiro.RedisSessionDAO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ShiroConfig {
    @Value("${spring.redis.host}")
    String redisHost;
    @Value("${spring.redis.port}")
    String redisPort;

    @Bean(name = "shiroFilterFactoryBean")
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(DefaultSecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        return shiroFilterFactoryBean;
    }

    @Bean
    public DefaultWebSecurityManager getDefaultSecurityManager(Realm realm) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(realm);
        // 自定义Session管理，使用redis
//        securityManager.setSessionManager(getDefaultWebSessionManager());
        securityManager.setSessionManager(getMyShiroSessionManager());
        // 自定义缓存实现，使用redis
        securityManager.setCacheManager(getRedisCacheManager());
        return securityManager;
    }

    @Bean
    public Realm getLoginRealm() {
        LoginRealm loginRealm = new LoginRealm();
        HashedCredentialsMatcher credentialsMatcher = new HashedCredentialsMatcher();
        credentialsMatcher.setHashAlgorithmName("MD5");
        credentialsMatcher.setHashIterations(88);
        loginRealm.setCredentialsMatcher(credentialsMatcher);
        return loginRealm;
    }


    public RedisManager getRedisManager() {
        RedisManager redisManager = new RedisManager();
        redisManager.setHost(redisHost + ":" + redisPort);
        return redisManager;
    }
    @Bean("redisSessionDao")
    public RedisSessionDAO getRedisSessionDAO() {
        RedisSessionDAO redisSessionDAO = new RedisSessionDAO();
        redisSessionDAO.setRedisManager(getRedisManager());
        return redisSessionDAO;
    }


    public MyShiroSessionManager getMyShiroSessionManager(){
        MyShiroSessionManager sessionManager = new MyShiroSessionManager();
        sessionManager.setSessionDAO(getRedisSessionDAO());
        return sessionManager;
    }
    public RedisCacheManager getRedisCacheManager() {
        RedisCacheManager redisCacheManager = new RedisCacheManager();
        redisCacheManager.setRedisManager(getRedisManager());
        return redisCacheManager;
    }
}
