package com.itzhang.service.impl;

import com.itzhang.mapper.RoleMapper;
import com.itzhang.mapper.UserMapper;
import com.itzhang.pojo.User;
import com.itzhang.service.UserService;
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
    public String getRoleByRoleId(String id) {
        return roleMapper.getRoleByRoleId(id);
    }

    @Override
    public Set<String> getAuthByRoleId(String id) {
        return roleMapper.getAuthByRoleId(id);
    }
}
