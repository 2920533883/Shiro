package com.itzhang.service;

import com.itzhang.pojo.User;

import java.util.List;
import java.util.Set;

public interface UserService {
    void insertUser(User user);

    List<User> getUsers(Integer pageNum, Integer pageSize);

    User getUserById(String id);

    User getUserByUsername(String username);

    void deleteUserById(String id);

    void updatePasswordById(String id, String password);

    void updateRoleById(String id, String role_id);
}
