package com.baixiang.security;

import com.baixiang.exception.UserNotFoundException;
import com.baixiang.model.mongo.Role;
import com.baixiang.model.jpa.User;
import com.baixiang.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;

/**
 * Created by shenjj on 2017/5/17.
 */

@Component
public class JpaRealm extends AuthorizingRealm {
    @Autowired
    UserService userService;

//    @Autowired
//    RoleService roleService;

    public JpaRealm() {
        setName("hibernateRealm"); //This name must match the name in the User class's getPrincipals() method
//        setCredentialsMatcher(new Sha256CredentialsMatcher());
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
//        Integer userId = (Integer) principals.fromRealm(getName()).iterator().next();
//        User user = userRepository.getById(userId);
        Long currentLoginId = (Long) principals.getPrimaryPrincipal();
        User user = userService.getById(currentLoginId);

//        System.out.printf("user=" + user.toString());
        if (user != null) {
            SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
            HashSet<Role> roleHashSet= (HashSet<Role>) user.getRoleSet();
            if(null!=roleHashSet){
                for (Role role : roleHashSet) {
                    info.addRole(role.getName());
                    info.addStringPermissions(role.getPermissions());
                }
            }
            return info;
        } else {
            return null;
        }
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
        if (null == token) {
            return null;
        }
        User user = userService.getByName(token.getUsername());
//        System.out.printf("user=" + user.toString());
        if (user != null) {
            return new SimpleAuthenticationInfo(user.getId(), user.getPass(), getName());
        } else {
            throw new UserNotFoundException();
//            return null;
        }
    }
}
