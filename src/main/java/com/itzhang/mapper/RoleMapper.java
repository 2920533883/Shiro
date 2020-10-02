package com.itzhang.mapper;

import com.itzhang.pojo.Role;
import com.itzhang.pojo.RoleAuth;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Set;

@Mapper
public interface RoleMapper {
    Role getRoleByRoleId(String role_id);

    void addRole(Role role);

    void deleteRoleByRoleId(String role_id);
}
