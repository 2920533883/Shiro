package com.itzhang.controller;

import com.itzhang.pojo.Auth;
import com.itzhang.pojo.R;
import com.itzhang.pojo.Role;
import com.itzhang.pojo.User;
import com.itzhang.service.RoleAuthService;
import com.itzhang.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Api(tags = "管理员模块")
@RestController("/user")
@Transactional(isolation = Isolation.READ_COMMITTED)
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
    @GetMapping("/getUsers/pageNum/{pageNum}/pageSize/{pageSize}")
    @RequiresPermissions("user:get")
    public R getUsers(@PathVariable Integer pageNum, @PathVariable Integer pageSize) {
        List<User> users = userService.getUsers(pageNum, pageSize);
        List<Map<String, Object>> res = new ArrayList<>();
        for (User user : users) {
            Map<String, Object> map = new HashMap<>();
            Role role = roleAuthService.getRole(user.getRole_id());
            List<Auth> auth = roleAuthService.getAuth(user.getRole_id());
            map.put("user", user);
            map.put("role", role);
            map.put("auth", auth);
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
    @PutMapping("/insertUser/username/{username}/password/{password}/role_id/{role_id}")
    @RequiresPermissions("user:insert")
    public R insertUser(User user) {
        User u = userService.getUserByUsername(user.getUsername());
        if (u != null) return new R(409, "用户名已存在！", null);
        userService.insertUser(user);
        return new R(200, "注册成功！", null);
    }

    @ApiOperation(value = "修改管理员密码", notes = "需要权限 user:update")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "用户ID"),
            @ApiImplicitParam(name = "password", value = "新密码")
    })
    @PutMapping("/updatePassword/id/{id}/password/{password}")
    @RequiresPermissions("user:update")
    public R updatePassword(@PathVariable String id, @PathVariable String password) {
        userService.updatePasswordById(id, password);
        return new R(200, "修改成功！", null);
    }

    @ApiOperation(value = "修改管理员角色", notes = "需要权限 user:update")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "用户ID"),
            @ApiImplicitParam(name = "role_id", value = "角色ID")
    })
    @PutMapping("/updateRole/id/{id}/role_id/{role_id}")
    @RequiresPermissions("user:update")
    public R updateRole(@PathVariable String id, @PathVariable String role_id) {
        userService.updateRoleById(id, role_id);
        return new R(200, "修改成功！", null);
    }

    @ApiOperation(value = "删除管理员", notes = "需要权限 user:delete")
    @ApiImplicitParam(name = "id", value = "用户ID")
    @DeleteMapping("/deleteUser/id/{id}")
    @RequiresPermissions("user:delete")
    public R deleteUser(@PathVariable String id) {
        userService.deleteUserById(id);
        return new R(200, "删除成功！", null);
    }
}
