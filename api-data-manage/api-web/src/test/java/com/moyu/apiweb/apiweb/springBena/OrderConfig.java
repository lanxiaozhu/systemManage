package com.moyu.apiweb.apiweb.springBena;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Auther: wishm
 * @Date: 2019/4/11 23:50
 * @Description:
 */
@Configuration
public class OrderConfig {
    //从上面的输出结果可以看到bean在javaConfig的显性配置下即用@Bean的注解的情况下bean的名字为其方法名。
    @Bean(initMethod = "init",destroyMethod = "destory")
    public Teacher get全局搜搜我(){
        return  new Teacher();
    }




}
