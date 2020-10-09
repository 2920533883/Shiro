package com.itzhang.controller;

import com.itzhang.pojo.Auth;
import com.itzhang.pojo.R;
import com.itzhang.service.RoleAuthService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Api(tags = "角色及权限管理模块")
@RestController
public class RoleAuthController {
    @Autowired
    RoleAuthService roleAuthService;

    @ApiOperation(value = "增加某角色的权限", notes = "需要权限 role:insert")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "role_id", value = "角色ID", paramType = "String"),
            @ApiImplicitParam(name = "auth_id", value = "权限ID", paramType = "String")
    })
    @RequiresPermissions("role:insert")
    @PutMapping("/role/addRoleAuth")
    public R addRoleAuth(@RequestParam String role_id, @RequestParam String auth_id) {
        roleAuthService.addRoleAuth(role_id, auth_id);
        return new R(200, "添加成功！", null);
    }

    @ApiOperation(value = "获取某角色的权限", notes = "需要权限 role:get")
    @ApiImplicitParam(name = "role_id", value = "角色ID", paramType = "String")
    @RequiresPermissions("role:get")
    @GetMapping("/role/getRoleAuth/{role_id}")
    public R getRoleAuth(@PathVariable String role_id) {
        List<Auth> auths = roleAuthService.getAuth(role_id);
        return new R(200, "获取成功！", auths);
    }
    @ApiOperation(value = "删除某角色的某权限", notes = "需要权限 role:delete")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "role_id", value = "角色ID", paramType = "String"),
            @ApiImplicitParam(name = "auth_id", value = "权限ID", paramType = "String")
    })
    @RequiresPermissions("role:delete")
    @DeleteMapping("/role/deleteRoleAuth/{role_id}")
    public R deleteRoleAuth(@PathVariable String role_id, @RequestParam String auth_id) {
        roleAuthService.deleteRoleAuth(role_id, auth_id);
        return new R(200, "删除成功！", null);
    }

    @ApiOperation(value = "增加角色", notes = "需要权限 role:insert")
    @ApiImplicitParam(name = "role_name", value = "角色名", paramType = "String")
    @RequiresPermissions("role:insert")
    @PutMapping("/role/addRole")
    public R addRole(@RequestParam String role_name) {
        roleAuthService.addRole(role_name);
        return new R(200, "添加成功！", null);
    }

    @ApiOperation(value = "删除角色", notes = "需要权限 role:delete")
    @ApiImplicitParam(name = "role_id", value = "角色ID", paramType = "String")
    @RequiresPermissions("role:delete")
    @DeleteMapping("/role/deleteRole/{role_id}")
    public R deleteRole(@PathVariable String role_id) {
        roleAuthService.deleteRole(role_id);
        return new R(200, "删除成功！", null);
    }

    @ApiOperation(value = "增加权限", notes = "需要权限 auth:insert")
    @ApiImplicitParam(name = "auth_name", value = "权限名", paramType = "String")
    @RequiresPermissions("auth:insert")
    @PutMapping("/auth/addAuth")
    public R addAuth(@RequestParam String auth_name) {
        roleAuthService.addAuth(auth_name);
        return new R(200, "添加成功！", null);
    }

    @ApiOperation(value = "删除权限", notes = "需要权限 auth:delete")
    @ApiImplicitParam(name = "auth_id", value = "权限ID", paramType = "String")
    @RequiresPermissions("auth:delete")
    @DeleteMapping("/auth/deleteAuth/{auth_id}")
    public R deleteAuth(@PathVariable String auth_id) {
        roleAuthService.deleteAuth(auth_id);
        return new R(200, "删除成功！", null);
    }

    @ApiOperation(value = "获取所有权限", notes = "需要权限 auth:get")
    @RequiresPermissions("auth:get")
    @GetMapping("/auth/getAllAuth")
    public R getAllAuth() {
        List<Auth> auths = roleAuthService.getAllAuth();
        return new R(200, "获取成功！", auths);
    }
}
