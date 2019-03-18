1.mvn pom  添加shiro依赖
核心代码：
api-data-manage\api-web\src\main\java\com\moyu\apiweb\apiweb\shiro


api—web模块中引入了Shiro权限控制
    简单介绍以下：
    1.引入pom shiro包
    2.添加自定义授权认证类 extends AuthorizingRealm | 从英文上可以认为 用户的领域权限
    //ShiroConfig类 ！
    3.@Configuration 注解是标识类为 beans 
    3.1： 自定义授权类（as 规则） 通过注解方式@Bean 声明对象被spring管理
    3.2： 向安全中心注册（策略） 规则
    3.3： ShiroFilterFactoryBean（as 场景） 绑定不同的策略 
    --
    more 开启Shiro的注解(如@RequiresRoles,@RequiresPermissions)
    3.4：DefaultAdvisorAutoProxyCreator 
    3.5： AuthorizationAttributeSourceAdvisor 
    --
    4.@ControllerAdvice 异常拦截Aop  配合 @ExceptionHandler(HttpRequestMethodNotSupportedException.class) 注解
    5.RequestPermissionFilter 拦截为登陆的请求  
