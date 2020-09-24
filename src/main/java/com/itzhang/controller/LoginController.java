package com.itzhang.controller;

import com.itzhang.pojo.R;
import com.itzhang.pojo.User;
import com.itzhang.service.LoginService;
import com.itzhang.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
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
    @PostMapping("/login/username/{username}/password/{password}")
    public R login(@PathVariable String password, @PathVariable String username) {
        Map<String, Object> res = loginService.login(username, password);
        return new R(200, "登陆成功！", res);
    }

    @PutMapping("/register/username/{username}/password/{password}/role_id/{role_id}")
    public R register(User user) {
        User u = userService.getUserByUsername(user.getUsername());
        if (u != null) return new R(409, "用户名已存在！", null);
        loginService.register(user);
        return new R(200, "注册成功！", null);
    }


    @ApiOperation("管理员注销")
    @PostMapping("/logout/username/{username}")
    public R logout(@PathVariable String username) {
        if (loginService.logout(username)) return new R(200, "注销成功！", null);
        else return new R(400, "请先登录！", null);
    }
}
