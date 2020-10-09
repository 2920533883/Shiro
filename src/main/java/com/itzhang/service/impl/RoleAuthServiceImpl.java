package com.itzhang.service.impl;

import com.itzhang.mapper.AuthMapper;
import com.itzhang.mapper.RoleAuthMapper;
import com.itzhang.mapper.RoleMapper;
import com.itzhang.pojo.Auth;
import com.itzhang.pojo.Role;
import com.itzhang.pojo.RoleAuth;
import com.itzhang.service.RoleAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service("roleAuthService")
@Transactional(isolation = Isolation.READ_UNCOMMITTED)
public class RoleAuthServiceImpl implements RoleAuthService {
    @Autowired
    private RoleAuthMapper roleAuthMapper;
    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private AuthMapper authMapper;
    @Override
    public List<Auth> getAuth(String role_id) {
        List<String> authIds = roleAuthMapper.getAuthIdByRoleId(role_id);
        List<Auth> authList = new ArrayList<>();
        for (String authId: authIds){
            Auth auth = authMapper.getAuthByAuthId(authId);
            authList.add(auth);
        }
        return authList;
    }

    @Override
    public Role getRole(String id) {
        return roleMapper.getRoleByRoleId(id);
    }

    @Override
    public void addRoleAuth(String role_id, String auth_id) {
        roleAuthMapper.addRoleAuth(new RoleAuth(null, role_id, auth_id));
    }

    @Override
    public void addAuth(String auth_name) {
        authMapper.addAuth(new Auth(null, auth_name));
    }

    @Override
    public void addRole(String role_name) {
        roleMapper.addRole(new Role(null, role_name));
    }

    @Override
    public void deleteRole(String role_id) {
        // 从角色表中删除角色相关数据
        roleMapper.deleteRoleByRoleId(role_id);
        // 从角色与权限表中删除角色相关数据
        roleAuthMapper.deleteRoleAuthByRoleId(role_id);
    }

    @Override
    public void deleteAuth(String auth_id) {
        authMapper.deleteAuthByAuthId(auth_id);
        roleAuthMapper.deleteRoleAuthByAuthId(auth_id);
    }

    @Override
    public List<Auth> getAllAuth() {
        return authMapper.getAllAuth();
    }

    @Override
    public void deleteRoleAuth(String role_id, String auth_id) {
        roleAuthMapper.deleteROleAuthByRoleAuthId(role_id, auth_id);
    }
}
