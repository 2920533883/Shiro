package com.itzhang.service;

import com.itzhang.pojo.User;

import java.util.Map;

public interface LoginService {
    Map<String, Object> login(String username, String password);
    boolean logout(String username);
}
