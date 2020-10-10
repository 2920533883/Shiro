package com.itzhang.realm;

import com.itzhang.entity.Auth;
import com.itzhang.entity.Role;
import com.itzhang.entity.User;
import com.itzhang.service.RoleAuthService;
import com.itzhang.service.UserService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class LoginRealm extends AuthorizingRealm {


    @Autowired
    UserService userService;
    @Autowired
    RoleAuthService roleAuthService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        String username = (String) principals.getPrimaryPrincipal();
        // 获取用户角色、权限信息
        User user = userService.getUserByUsername(username);
        Role role = roleAuthService.getRole(user.getRole_id());
        List<Auth> auth = roleAuthService.getAuth(user.getRole_id());
        Set<String> authName = new HashSet<>();
        auth.forEach(auth1 -> authName.add(auth1.getAuth_name()));
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        // 设置权限
        authorizationInfo.setStringPermissions(authName);
        // 设置角色
        authorizationInfo.setRoles(Collections.singleton(role.getRole_name()));
        return authorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String username = (String) token.getPrincipal();
        User user = userService.getUserByUsername(username);
        if (!ObjectUtils.isEmpty(user)) {
            String password = user.getPassword();
            String salt = user.getSalt();
            return new SimpleAuthenticationInfo(username, password, ByteSource.Util.bytes(salt), this.getName());
        }
        return null;
    }
}
