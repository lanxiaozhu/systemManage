package com.moyu.apiweb.apiweb.shiro;

import com.moyu.core.user.domain.MyMenu;
import com.moyu.core.user.domain.MyRole;
import com.moyu.core.user.domain.MyUser;
import com.moyu.core.user.service.LoginService;
import com.moyu.core.user.service.RoleMenuRelationService;
import com.moyu.core.user.service.UserRoleRelationService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


/**
 * 自定义shiro realm
 * AuthorizingRealm 授权范围
 * <p>
 * 通过Realm来获取应用程序中的用户、角色及权限信息
 */
public class MyShiroRealm extends AuthorizingRealm {

    private final static Logger logger = LoggerFactory.getLogger(MyShiroRealm.class);


    @Autowired
    private LoginService loginService;

    @Autowired
    private UserRoleRelationService userRoleRelationService;

    @Autowired
    private RoleMenuRelationService roleMenuRelationService;
    /**
     * 链接配置了相应的权限或者shiro标签才会执行此方法否则不会执行
     * 简单的身份认证没有权限的控制的话，那么这个方法可以不进行实现，直接返回null即可
     * 赋予角色和权限
     *
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        logger.info("权限配置-->doGetAuthorizationInfo");
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        MyUser userInfo = (MyUser) principalCollection.getPrimaryPrincipal();

        //1、获取角色信息
        List<MyRole> myRoles = userRoleRelationService.selectdRole(userInfo.getId());
        for (MyRole role: myRoles) {
            //1.1授权 authorization 添加角色名称集合
            authorizationInfo.addRole(role.getRoleName());

            //2、获取权限信息
            List<MyMenu> myMenus = roleMenuRelationService.selectdMenu(role.getId());
            for (MyMenu menu: myMenus) {
                //2.2添加权限集合
                authorizationInfo.addStringPermission(menu.getPermissionCode());
            }

        }
        /*authorizationInfo.setRoles(getRolesByUserName());
        authorizationInfo.setStringPermissions(getPermissionByUserName());*/

       /* for(SysRole role:userInfo.getRoles()){
            //授权 authorization 添加角色名称集合
            authorizationInfo.addRole(role.getRoleName());
            for(Menu p:role.getMenus()){
                //添加权限集合
                authorizationInfo.addStringPermission(p.getPermissionCode());
            }
        }*/
        return authorizationInfo;
    }

   /* private Set<String> getPermissionByUserName() {
        Set<String> sets = new HashSet<>();
        sets.add("user:delete");
        sets.add("user:add");
        return sets;
    }

    private Set<String> getRolesByUserName() {
        Set<String> sets = new HashSet<>();
        sets.add("admin");
        sets.add("user");
        return sets;
    }*/

    /**
     * 用户认证
     *
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        logger.info("用户认证-->");
        // 获取用户密码
        String loginName = (String) token.getPrincipal();
        MyUser user = loginService.getUser(loginName);
        if (user == null) {
            //没找到帐号
            throw new UnknownAccountException();
        }
        //交给AuthenticatingRealm使用CredentialsMatcher进行密码匹配
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                user,
                user.getPassword(),
                //ByteSource.Util.bytes("salt"), salt=username+salt,采用明文访问时，不需要此句
                getName());
        //session中不需要保存密码
        //user.remove("password");
        //将用户信息放入session中
        //SecurityUtils.getSubject().getSession().setAttribute("user", user);
        return authenticationInfo;
    }

    @Override
    public void clearCachedAuthorizationInfo(PrincipalCollection principals) {
        logger.info("用户退出===清除用户权限缓存");
        super.clearCachedAuthorizationInfo(principals);
    }

    @Override
    public void clearCache(PrincipalCollection principals) {
        logger.info("用户退出===清除用户缓存");
        super.clearCache(principals);
    }
}
