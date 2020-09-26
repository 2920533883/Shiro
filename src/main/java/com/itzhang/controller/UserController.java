package com.itzhang.controller;

import com.itzhang.pojo.R;
import com.itzhang.pojo.User;
import com.itzhang.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.Set;

@Api(tags = "管理员模块")
@RestController
public class UserController {
    @Autowired
    UserService userService;

    @ApiOperation(value = "获取所有管理员信息", notes = "需要权限 user:get")
    @GetMapping("/getAllUser")
    @RequiresPermissions("user:get")
    public R getAllUser() {
        List<User> users = userService.getAllUser();
        for (User user : users) {
            String role = userService.getRoleByRoleId(user.getRole_id());
            user.setRole(role);
            Set<String> auth = userService.getAuthByRoleId(user.getRole_id());
            user.setAuth(auth);
        }
        return new R(200, "获取成功", users);
    }

    @ApiOperation(value = "添加管理员", notes = "需要权限 user:insert")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username", value = "用户名"),
            @ApiImplicitParam(name = "password", value = "密码"),
            @ApiImplicitParam(name = "role_id", value = "角色Id")
    })
    @PutMapping("/insertUser/username/{username}/password/{password}/role_id/{role_id}")
    @RequiresPermissions("user:insert")
    public R insertUser(User user) {
        User u = userService.getUserByUsername(user.getUsername());
        if (u != null) return new R(409, "用户名已存在！", null);
        userService.insertUser(user);
        return new R(200, "注册成功！", null);
    }
}
