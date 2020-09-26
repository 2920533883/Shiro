package com.itzhang.config;

import com.itzhang.manager.MyShiroSessionManager;
import com.itzhang.realm.LoginRealm;
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
        // 自定义会话管理
        securityManager.setSessionManager(getMyShiroSessionManager());
        // 自定义缓存管理，使用redis
        securityManager.setCacheManager(getRedisCacheManager());
        return securityManager;
    }

    @Bean
    public Realm getLoginRealm() {
        LoginRealm loginRealm = new LoginRealm();
        HashedCredentialsMatcher credentialsMatcher = new HashedCredentialsMatcher();
        // 设置加密算法
        credentialsMatcher.setHashAlgorithmName("MD5");
        // 设置Hash次数
        credentialsMatcher.setHashIterations(88);
        // 设置凭证匹配器
        loginRealm.setCredentialsMatcher(credentialsMatcher);
        return loginRealm;
    }

    // shiro-redis插件 RedisManager
    public RedisManager getRedisManager() {
        RedisManager redisManager = new RedisManager();
        redisManager.setHost(redisHost + ":" + redisPort);
        return redisManager;
    }

    // shiro-redis插件 RedisSessionDAO 将Session存储到redis中 因为要更改默认的存储前缀，所以把它注入到spring容器。
    @Bean("redisSessionDao")
    public RedisSessionDAO getRedisSessionDAO() {
        RedisSessionDAO redisSessionDAO = new RedisSessionDAO();
        redisSessionDAO.setRedisManager(getRedisManager());
        return redisSessionDAO;
    }

    // 自定义会话管理器，作用是在创建会话时，将token的值赋给会话的sessionId
    public MyShiroSessionManager getMyShiroSessionManager(){
        MyShiroSessionManager sessionManager = new MyShiroSessionManager();
        sessionManager.setSessionDAO(getRedisSessionDAO());
        return sessionManager;
    }

    // shiro-redis插件 RedisCacheManager 将认证信息以及权限信息存储到redis中
    public RedisCacheManager getRedisCacheManager() {
        RedisCacheManager redisCacheManager = new RedisCacheManager();
        redisCacheManager.setRedisManager(getRedisManager());
        return redisCacheManager;
    }
}
