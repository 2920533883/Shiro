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

    private static final String AUTH_TOKEN = "token";

    private static final String REFERENCED_SESSION_ID_SOURCE = "Stateless request";


    public MyShiroSessionManager() {
        super();
    }

    /**
     * 获取sessionId，原本是根据sessionKey来获取一个sessionId
     * 重写的部分多了一个把获取到的token设置到request的部分。这是因为app调用登陆接口的时候，是没有token的，
     * 登陆成功后，产生了token,我们把它放到request中，返回结
     * 果给客户端的时候，把它从request中取出来，并且传递给客户端，客户端每次带着这个token过来，
     * 就相当于是浏览器的cookie的作用，也就能维护会话了
     *
     * @param request
     * @param response
     * @return
     */
    @Override
    protected Serializable getSessionId(ServletRequest request, ServletResponse response) {
        //获取请求头中的 AUTH_TOKEN 的值，如果请求头中有AUTH_TOKEN则其值为token。shiro就是通过token来控制的
        String token = WebUtils.toHttp(request).getHeader(AUTH_TOKEN);
        if (StringUtils.isEmpty(token)) {
            //如果没有携带id 返回空
            return null;
        } else {
            //请求头中如果有token, 设值
            request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID_SOURCE, REFERENCED_SESSION_ID_SOURCE);
            request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID, token);
            request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID_IS_VALID, Boolean.TRUE);
            return token;
        }
    }

}
