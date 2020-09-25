package com.itzhang.service.impl;

import com.itzhang.mapper.UserMapper;
import com.itzhang.service.LoginService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.crazycake.shiro.RedisSessionDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class LoginServiceImpl implements LoginService {
    @Autowired
    RedisSessionDAO redisSessionDAO;

    @Override
    public Map<String, Object> login(String username, String password) {
        redisSessionDAO.setKeyPrefix(username + ":");
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        subject.login(token);
        String id = (String) subject.getSession().getId();
        Map<String, Object> res = new HashMap<>();
        res.put("username", username);
        res.put("token", id);
        return res;
    }

    @Override
    public boolean logout(String username) {
        Subject subject = SecurityUtils.getSubject();
        System.out.println(subject.getSession().getId());
        subject.logout();
        return true;
    }
}
