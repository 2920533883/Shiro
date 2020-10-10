package com.itzhang.mapper;

import com.itzhang.entity.RoleAuth;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface RoleAuthMapper {
    List<String> getAuthIdByRoleId(String role_id);
    void addRoleAuth(RoleAuth roleAuth);

    void deleteRoleAuthByRoleId(String role_id);

    void deleteRoleAuthByAuthId(String auth_id);

    void deleteROleAuthByRoleAuthId(@Param("role_id") String role_id,@Param("auth_id") String auth_id);
}
