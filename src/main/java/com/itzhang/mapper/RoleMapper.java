package com.itzhang.mapper;

import com.itzhang.entity.Role;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RoleMapper {

    List<Role> getAllRole();

    Role getRoleByRoleId(String role_id);

    void addRole(Role role);

    void deleteRoleByRoleId(String role_id);
}
