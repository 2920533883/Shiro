package com.itzhang.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.Set;
@Mapper
public interface RoleMapper {
    String getRoleByRoleId(String id);
    Set<String> getAuthByRoleId(String id);
}
