package com.itzhang.realm;

import com.itzhang.mapper.RoleMapper;
import com.itzhang.mapper.UserMapper;
import com.itzhang.pojo.User;
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
import java.util.Set;

public class LoginRealm extends AuthorizingRealm {


    @Autowired
    UserMapper userMapper;

    @Autowired
    RoleMapper roleMapper;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        String username = (String) principals.getPrimaryPrincipal();
        User user = userMapper.getUserByUsername(username);
        String role = roleMapper.getRoleByRoleId(user.getRole_id());
        Set<String> auth = roleMapper.getAuthByRoleId(user.getRole_id());
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        authorizationInfo.setStringPermissions(auth);
        authorizationInfo.setRoles(Collections.singleton(role));
        return authorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String username = (String) token.getPrincipal();
        User user = userMapper.getUserByUsername(username);
        if (!ObjectUtils.isEmpty(user)) {
            String password = user.getPassword();
            String salt = user.getSalt();
            return new SimpleAuthenticationInfo(username, password, ByteSource.Util.bytes(salt), this.getName());
        }
        return null;
    }
}
