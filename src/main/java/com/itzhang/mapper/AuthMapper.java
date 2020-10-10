package com.itzhang.mapper;

import com.itzhang.entity.Auth;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AuthMapper {
    List<Auth> getAllAuth();

    Auth getAuthByAuthId(String auth_id);

    void addAuth(Auth auth);

    void deleteAuthByAuthId(String auth_id);
}
