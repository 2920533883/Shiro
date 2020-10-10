package com.itzhang.service.impl;

import com.itzhang.mapper.UserMapper;
import com.itzhang.entity.User;
import com.itzhang.service.UserService;
import com.itzhang.utils.SaltUtil;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public List<User> getUsers(Integer pageNum, Integer pageSize) {
        int start = (pageNum-1)*pageSize;
        int offset = pageSize;
        return userMapper.getUsers(start, offset);
    }

    @Override
    public User getUserById(String id) {
        return userMapper.getUserById(id);
    }

    @Override
    public User getUserByUsername(String username) {
        return userMapper.getUserByUsername(username);
    }

    @Override
    public void deleteUserById(String id) {
        userMapper.deleteUserById(id);
    }

    @Override
    public void updatePasswordById(User user) {
        String password = user.getPassword();
        String salt = SaltUtil.getSalt();
        Md5Hash md5Hash = new Md5Hash(password, salt, 88);
        password = md5Hash.toHex();
        user.setPassword(password);
        user.setSalt(salt);
        userMapper.updatePasswordById(user);
    }

    @Override
    public void updateRoleById(User user) {
        userMapper.updateRoleById(user);
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
}
