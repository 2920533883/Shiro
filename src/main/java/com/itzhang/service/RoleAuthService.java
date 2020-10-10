package com.itzhang.service;

import com.itzhang.entity.Auth;
import com.itzhang.entity.Role;
import com.itzhang.entity.RoleAuth;

import java.util.List;

public interface RoleAuthService {
    List<Auth> getAuth(String role_id);

    List<Auth> getAllAuth();

    List<Role> getAllRole();

    Role getRole(String id);

    void addRoleAuth(RoleAuth roleAuth);

    void addAuth(Auth auth);

    void addRole(Role role);

    void deleteRole(String role_id);

    void deleteAuth(String auth_id);


    void deleteRoleAuth(String role_id, String auth_id);
}
