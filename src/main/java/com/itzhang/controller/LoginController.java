package com.itzhang.controller;

import com.itzhang.pojo.R;
import com.itzhang.service.LoginService;
import com.itzhang.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Api(tags = "登录注销模块")
@RestController
public class LoginController {
    @Autowired
    LoginService loginService;
    @Autowired
    UserService userService;

    @ApiOperation("管理员登录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username", value = "用户名", paramType = "String"),
            @ApiImplicitParam(name = "password", value = "密码", paramType = "String")
    })
    @PostMapping("/login")
    public R login(@RequestParam("username") String username, @RequestParam("password") String password) {
        Map<String, Object> res = loginService.login(username, password);
        return new R(200, "登陆成功！", res);
    }

    @ApiOperation("管理员注销")
    @PostMapping("/logout")
    @RequiresPermissions("user:get")
    public R logout() {
        loginService.logout();
        return new R(200, "注销成功！", null);
    }
}
