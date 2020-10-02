package com.itzhang.mapper;

import com.itzhang.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserMapper {
    List<User> getUsers(@Param("start")Integer start,@Param("offset") Integer offset);
    User getUserByUsername(String username);
    void insertUser(User user);
    void deleteUserById(String id);
    void updatePasswordById(@Param("id")String id, @Param("password")String password, @Param("salt") String salt);
    void updateRoleById(@Param("id")String id, @Param("role_id")String role_id);

}
