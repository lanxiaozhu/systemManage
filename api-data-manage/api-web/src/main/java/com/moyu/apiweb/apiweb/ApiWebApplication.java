package com.moyu.apiweb.apiweb;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * Service 及 ServiceImpl均需在com.moyu包下
 */

/**
 * 作用：Spring Boot会自动根据你jar包的依赖来自动配置项目。
 * 例如当你项目下面有HSQLDB的依赖时，Spring Boot会创建默认的内存数据库的数据源DataSource，如果你自己创建了DataSource，Spring Boot就不会创建默认的DataSource。
 *
 * 个人理解： 如果你用了Mybatis，它的配置文件中会指定 数据库相关的参数，这个时候就用exclude={DataSourceAutoConfiguration.class}, 让spring-boot不要根据Maven中依赖自动配置了。
 */


/**
 * springboot入口类,此类需要在所有用到的package上层
 * exclude = {DataSourceAutoConfiguration.class}
 * 禁用springboot默认加载的application.properties单数据源配置
 */
@SpringBootApplication(scanBasePackages="com.moyu",exclude = {DataSourceAutoConfiguration.class})
@MapperScan("com.moyu.core.user.dao")//扫描Mapper
public class ApiWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApiWebApplication.class, args);
    }
}
/**

exclude = {DataSourceAutoConfiguration.class}

Description:

The dependencies of some of the beans in the application context form a cycle:

   homeController (field private LoginService com.moyu.apiweb.apiweb.controller.sys.HomeController.loginService)
      ↓
   loginService (field private com.moyu.core.user.dao.mapper.UserMapper LoginServiceImpl.userMapper)
      ↓
   userMapper defined in file\
      ↓
   sqlSessionFactory defined in class path resource [org/mybatis/spring/boot/autoconfigure/MybatisAutoConfiguration.class]
┌─────┐
|  roundRobinDataSouceProxy defined in class path resource [com/moyu/util/datasource/DataBaseConfiguration.class]
↑     ↓
|  master defined in class path resource [com/moyu/util/datasource/DataBaseConfiguration.class]
↑     ↓
|  org.springframework.boot.autoconfigure.jdbc.DataSourceInitializerInvoker
└─────┘
 */
