package com.itzhang.service;

import com.itzhang.pojo.Auth;
import com.itzhang.pojo.Role;

import java.util.List;

public interface RoleAuthService {
    List<Auth> getAuth(String role_id);

    Role getRole(String id);

    void addRoleAuth(String role_id, String auth_id);

    void addAuth(String auth_name);

    void addRole(String role_name);

    void deleteRole(String role_id);

    void deleteAuth(String auth_id);

    List<Auth> getAllAuth();

    void deleteRoleAuth(String role_id, String auth_id);
}
