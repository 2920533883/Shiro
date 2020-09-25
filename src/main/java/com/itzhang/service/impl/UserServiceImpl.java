package com.itzhang.service.impl;

import com.itzhang.mapper.RoleMapper;
import com.itzhang.mapper.UserMapper;
import com.itzhang.pojo.User;
import com.itzhang.service.UserService;
import com.itzhang.utils.SaltUtil;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RoleMapper roleMapper;

    @Override
    public List<User> getAllUser() {
        return userMapper.getAllUser();
    }

    @Override
    public User getUserByUsername(String username) {
        return userMapper.getUserByUsername(username);
    }

    @Override
    public void insertUser(User user) {
        String password = user.getPassword();
        String salt = SaltUtil.getSalt();
        Md5Hash md5Hash = new Md5Hash(password, salt, 88);
        user.setPassword(md5Hash.toHex());
        user.setSalt(salt);
        userMapper.insertUser(user);
    }

    @Override
    public String getRoleByRoleId(String id) {
        return roleMapper.getRoleByRoleId(id);
    }

    @Override
    public Set<String> getAuthByRoleId(String id) {
        return roleMapper.getAuthByRoleId(id);
    }
}
