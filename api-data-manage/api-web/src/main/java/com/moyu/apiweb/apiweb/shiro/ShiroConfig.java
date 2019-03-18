package com.moyu.apiweb.apiweb.shiro;

import com.moyu.apiweb.apiweb.interceptorRequest.RequestPermissionFilter;
import org.apache.shiro.codec.Base64;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.session.mgt.eis.MemorySessionDAO;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.server.session.DefaultWebSessionManager;

import javax.servlet.Filter;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * shiro配置
 * <p>
 * 注意：pom引入spring boot版本的shiro
 * shiro的配置类@Configuration类，可以写的很简洁，不用写AuthorizationAttributeSourceAdvisor的@Bean
 */
@Configuration
public class ShiroConfig {
    private static final Logger logger = LoggerFactory.getLogger(ShiroConfig.class);
    private final static String  PREFIX = "【Shiro结藕配置】";
    /**
     * 注入自定义ShiroRealm
     * @return
     */
    @Bean
    public MyShiroRealm customShiroRealm() {
        return new MyShiroRealm();
    }

    /**
     * 注入 管SessionId DefaultWebSessionManager
     * @return
     */
    @Bean
    public SessionManager sessionManager() {
        MySessionManager mySessionManager = new MySessionManager();
        return mySessionManager;
    }

    /**
     * 2.向安全中心 -->注册 -->自定义规则
     * @return
     */
    @Bean
    public SecurityManager securityManager() {
        DefaultWebSecurityManager defaultSecurityManager = new DefaultWebSecurityManager();
        defaultSecurityManager.setRealm(customShiroRealm());

        // 自定义session管理
        defaultSecurityManager.setSessionManager(sessionManager());
        return defaultSecurityManager;
    }


    /**
     * 3.向制造工厂 提交 安全中心（组件） ！成品
     *
     * @param securityManager
     * @return
     */
    @Bean(name = "shiroFilter")
    public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager) {

        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);


        Map<String, Filter> filtersMap = new LinkedHashMap<>();
        filtersMap.put("authc", new RequestPermissionFilter());
        shiroFilterFactoryBean.setFilters(filtersMap);

        // 如果不设置默认会自动寻找Web工程根目录下的"/login.jsp"页面
        shiroFilterFactoryBean.setLoginUrl("/login/auth");
        //未授权页面
        shiroFilterFactoryBean.setUnauthorizedUrl("/login/403");

        //拦截器
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<>();//LinkedHashMap 同样是一个HashMap,不过内部实现了 双向链表结构，可以保证顺序
        //2.退出登陆
        filterChainDefinitionMap.put("/login/logout", "logout");
        filterChainDefinitionMap.put("/api/**", "anon");
        //3.其他所有路径都会被拦截  authc
        //filterChainDefinitionMap.put("/**", "authc");

        //注册自己 定义definition chain约束
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);


        return shiroFilterFactoryBean;
    }



//    //因为我们的密码是加过密的，所以，如果要Shiro验证用户身份的话，需要告诉它我们用的是md5加密的，并且是加密了两次。同时我们在自己的Realm中也通过SimpleAuthenticationInfo返回了加密时使用的盐。这样Shiro就能顺利的解密密码并验证用户名和密码是否正确了。
//    @Bean
//    public HashedCredentialsMatcher hashedCredentialsMatcher() {
//        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
//        hashedCredentialsMatcher.setHashAlgorithmName("md5");//散列算法:这里使用MD5算法;
//        hashedCredentialsMatcher.setHashIterations(2);//散列的次数，比如散列两次，相当于 md5(md5(""));
//        return hashedCredentialsMatcher;
//    }


    /**
     * 开启Shiro的注解(如@RequiresRoles,@RequiresPermissions),
     * 需借助SpringAOP扫描使用Shiro注解的类,并在必要时进行安全逻辑验证
     * 配置以下两个bean即可实现此功能
     */
    @Bean
    public DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator() {

        DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
        advisorAutoProxyCreator.setProxyTargetClass(true);
        return advisorAutoProxyCreator;
    }

    /**
     * 开启aop注解支持，用于代理如@RequiresPermissions注解的控制器，进行权限控制(通过AOP检查是否存在shiro注解)
     * authorization 授权
     * Authentication 认证
     * @return
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor() {
        /**
         * 如果方法具有任何Shiro注释，则返回true，否则返回false。检查的注释是：
         * RequiresAuthentication
         * RequiresUser
         * RequiresGuest
         * RequiresRoles
         * RequiresPermissions
         *
         * AuthorizationAttributeSourceAdvisor 这个东西 在项目启动时会初始化扫描一部分maven 引入的包！在访问项目路径时 会扫描是否带有shiro注解  有返回true
         */
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager());
        return authorizationAttributeSourceAdvisor;
    }

}
