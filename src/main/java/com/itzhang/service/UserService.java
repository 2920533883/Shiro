package com.itzhang.service;

import com.itzhang.pojo.User;

import java.util.List;
import java.util.Set;

public interface UserService {
    void insertUser(User user);

    List<User> getAllUser();

    User getUserByUsername(String username);

    String getRoleByRoleId(String id);

    Set<String> getAuthByRoleId(String id);
}
