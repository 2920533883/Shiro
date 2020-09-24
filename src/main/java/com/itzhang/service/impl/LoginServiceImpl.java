package com.itzhang.service.impl;

import com.itzhang.utils.SaltUtil;
import com.itzhang.mapper.UserMapper;
import com.itzhang.pojo.User;
import com.itzhang.service.LoginService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.subject.Subject;
import org.crazycake.shiro.RedisSessionDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Service
public class LoginServiceImpl implements LoginService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    RedisSessionDAO redisSessionDAO;
    @Autowired
    RedisTemplate<String, Object> redisTemplate;

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
    public void register(User user) {
        String password = user.getPassword();
        String salt = SaltUtil.getSalt();
        Md5Hash md5Hash = new Md5Hash(password, salt, 88);
        user.setPassword(md5Hash.toHex());
        user.setSalt(salt);
        userMapper.register(user);
    }

    @Override
    public boolean logout(String username) {
        Set<String> keys = redisTemplate.keys(username + ":*");
        if (keys == null) return false;
        redisTemplate.delete(keys);
        return true;
    }
}
