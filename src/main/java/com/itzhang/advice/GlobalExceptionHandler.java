package com.itzhang.advice;

import com.itzhang.entity.R;
import org.apache.shiro.authc.CredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationException;
import org.springframework.dao.DataAccessException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;


@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ServletRequestBindingException.class)
    public R myServletRequestBindingException(Exception e){
        System.out.println("请求参数不可为空！");
        e.printStackTrace();
        return new R(400, "请求参数不可为空！", null);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public R myDataIntegrityViolationException(Exception e){
        System.out.println("请求参数不正确！");
        e.printStackTrace();
        return new R(400, "请求参数不正确！", null);
    }

    @ExceptionHandler(UnknownAccountException.class)
    public R myUnknownAccountExceptionHandler(UnknownAccountException e) {
        System.out.println("用户不存在！");
        e.printStackTrace();
        return new R(401, "该用户不存在！", null);
    }

    @ExceptionHandler(CredentialsException.class)
    public R myCredentialsExceptionHandler(CredentialsException e) {
        System.out.println("密码错误！");
        e.printStackTrace();
        return new R(401, "密码错误！", null);
    }

    @ExceptionHandler(AuthorizationException.class)
    public R myAuthorizationExceptionHandler(AuthorizationException e) {
        System.out.println("权限不足！");
        e.printStackTrace();
        return new R(403, "没有对应权限！", null);
    }


    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public R myHttpRequestMethodNotSupportedException(Exception e){
        System.out.println("请求不正确！");
        e.printStackTrace();
        return new R(405, "请求不正确" ,null);
    }

    @ExceptionHandler(Exception.class)
    public R mySQLException(Exception e) {
        e.printStackTrace();
        return new R(500, "服务器出现异常！", null);
    }

}
