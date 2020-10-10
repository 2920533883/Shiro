package com.itzhang.controller;

import com.itzhang.entity.R;
import com.itzhang.entity.Role;
import com.itzhang.entity.User;
import com.itzhang.service.RoleAuthService;
import com.itzhang.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@Api(tags = "管理员模块")
@RestController("/user")
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    RoleAuthService roleAuthService;

    @ApiOperation(value = "获取所有管理员信息", notes = "需要权限 user:get")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "当前页", dataType = "int"),
            @ApiImplicitParam(name = "pageSize", value = "每页个数", dataType = "int")
    })
    @GetMapping("/users")
    @RequiresPermissions("user:get")
    public R getUsers(@RequestParam Integer pageNum, @RequestParam Integer pageSize) {
        List<User> users = userService.getUsers(pageNum, pageSize);
        List<Map<String, Object>> res = new ArrayList<>();
        for (User user : users) {
            Map<String, Object> map = new HashMap<>();
            Role role = roleAuthService.getRole(user.getRole_id());
            map.put("user", user);
            map.put("role", role);
            res.add(map);
        }
        return new R(200, "获取成功！", res);
    }

    @ApiOperation(value = "添加管理员", notes = "需要权限 user:insert")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username", value = "用户名"),
            @ApiImplicitParam(name = "password", value = "密码"),
            @ApiImplicitParam(name = "role_id", value = "角色ID")
    })
    @PostMapping("/user")
    @RequiresPermissions("user:insert")
    public R insertUser(@RequestParam String username, @RequestParam String password, @RequestParam String role_id) {
        User u = userService.getUserByUsername(username);
        if (u != null) return new R(409, "用户名已存在！", null);
        User user = new User(null, username, password, null, role_id);
        userService.insertUser(user);
        return new R(200, "注册成功！", user);
    }

    @ApiOperation(value = "修改管理员密码", notes = "需要权限 user:update")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "用户ID"),
            @ApiImplicitParam(name = "password", value = "新密码")
    })
    @PutMapping("/user/password/{id}")
    @RequiresPermissions("user:update")
    public R updatePassword(@PathVariable String id, @RequestParam String password) {
        User user = new User(id, null, password, null, null);
        userService.updatePasswordById(user);
        return new R(200, "修改成功！", user);
    }

    @ApiOperation(value = "修改某管理员角色", notes = "需要权限 user:update")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "用户ID"),
            @ApiImplicitParam(name = "role_id", value = "角色ID")
    })
    @PutMapping("/user/role/{id}")
    @RequiresPermissions("user:update")
    public R updateRole(@PathVariable String id, @RequestParam String role_id) {
        User user = new User(id, null, null, null, role_id);
        userService.updateRoleById(user);
        return new R(200, "修改成功！", user);
    }

    @ApiOperation(value = "删除管理员", notes = "需要权限 user:delete")
    @ApiImplicitParam(name = "id", value = "用户ID")
    @DeleteMapping("/user/{id}")
    @RequiresPermissions("user:delete")
    public R deleteUser(@PathVariable String id) {
        userService.deleteUserById(id);
        return new R(200, "删除成功！", null);
    }
}
