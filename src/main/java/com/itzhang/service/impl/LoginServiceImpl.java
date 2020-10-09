package com.itzhang.service.impl;

import com.itzhang.service.LoginService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.crazycake.shiro.RedisSessionDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

@Service
@Transactional(isolation = Isolation.READ_COMMITTED)
public class LoginServiceImpl implements LoginService {
    @Autowired
    RedisSessionDAO redisSessionDAO;

    @Override
    public Map<String, Object> login(String username, String password) {
        // 设置redis存储前缀
        redisSessionDAO.setKeyPrefix("shiro:" + username + ":");
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
    public void logout() {
        Subject subject = SecurityUtils.getSubject();
        System.out.println(subject.getSession().getId());
        subject.logout();
    }
}
