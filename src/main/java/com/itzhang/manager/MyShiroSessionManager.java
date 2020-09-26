package com.itzhang.manager;

import org.apache.shiro.session.mgt.SessionKey;
import org.apache.shiro.web.servlet.ShiroHttpServletRequest;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.util.StringUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.Serializable;

public class MyShiroSessionManager extends DefaultWebSessionManager {

    private static final String REFERENCED_SESSION_ID_SOURCE = "Stateless request";


    public MyShiroSessionManager() {
        super();
    }

    @Override
    protected Serializable getSessionId(ServletRequest request, ServletResponse response) {
        //获取请求头中的token的值
        String token = WebUtils.toHttp(request).getHeader("token");
        if (StringUtils.isEmpty(token)) {
            //请求头中没有token，返回空
            return null;
        } else {
            request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID_SOURCE, REFERENCED_SESSION_ID_SOURCE);
            //请求头中如果有token, 将token的值赋值给sessionId
            request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID, token);
            // 将sessionId设为有效
            request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID_IS_VALID, Boolean.TRUE);
            return token;
        }
    }

}
