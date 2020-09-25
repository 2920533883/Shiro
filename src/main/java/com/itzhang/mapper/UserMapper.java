package com.itzhang.mapper;

import com.itzhang.pojo.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {
    List<User> getAllUser();
    User getUserByUsername(String username);
    void insertUser(User user);
}
