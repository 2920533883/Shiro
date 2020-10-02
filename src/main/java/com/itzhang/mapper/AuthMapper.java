package com.itzhang.mapper;

import com.itzhang.pojo.Auth;
import com.itzhang.pojo.RoleAuth;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Set;

@Mapper
public interface AuthMapper {
    List<Auth> getAllAuth();

    Auth getAuthByAuthId(String auth_id);

    void addAuth(Auth auth);

    void deleteAuthByAuthId(String auth_id);
}
