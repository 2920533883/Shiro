package com.itzhang.advice;

import com.itzhang.pojo.R;
import org.apache.shiro.authc.CredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLException;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(AuthorizationException.class)
    public R myAuthorizationExceptionHandler(AuthorizationException e) {
        e.printStackTrace();
        return new R(405, "权限不足！", null);
    }

    @ExceptionHandler(UnknownAccountException.class)
    public R myUnknownAccountExceptionHandler(UnknownAccountException e) {
        e.printStackTrace();
        System.out.println("用户不存在");
        return new R(401, "该用户不存在！", null);
    }

    @ExceptionHandler(CredentialsException.class)
    public R myCredentialsExceptionHandler(CredentialsException e) {
        e.printStackTrace();
        System.out.println("密码错误");
        return new R(401, "密码错误！", null);
    }

    @ExceptionHandler(Exception.class)
    public R mySQLException(Exception e) {
        e.printStackTrace();
        return new R(500, "服务器出现异常！", null);
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public R myHttpRequestMethodNotSupportedException(Exception e){
        System.out.println(e.getMessage());
        return new R(405, "请求方法不正确" ,null);
    }
}
