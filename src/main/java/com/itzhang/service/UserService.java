package com.itzhang.service;

import com.itzhang.entity.User;

import java.util.List;

public interface UserService {
    void insertUser(User user);

    List<User> getUsers(Integer pageNum, Integer pageSize);

    User getUserById(String id);

    User getUserByUsername(String username);

    void deleteUserById(String id);

    void updatePasswordById(User user);

    void updateRoleById(User user);
}
