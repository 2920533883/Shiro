package com.itzhang.mapper;

import com.itzhang.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserMapper {
    List<User> getUsers(@Param("start")Integer start,@Param("offset") Integer offset);
    User getUserByUsername(String username);
    User getUserById(String id);
    void insertUser(User user);
    void deleteUserById(String id);
    void updatePasswordById(User user);
    void updateRoleById(User user);

}
