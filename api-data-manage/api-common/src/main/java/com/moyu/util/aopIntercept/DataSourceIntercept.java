package com.moyu.util.aopIntercept;

import com.moyu.util.datasource.DynamicDataSourceHolder;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

/**
 * @Auther: guoxianjun
 * @Date: 2019/1/24 19:07
 * @Description: 拦截数据操作
 */
@Aspect
@Configuration
public class DataSourceIntercept {

    private final static Logger logger = LoggerFactory.getLogger(DataSourceIntercept.class);
    private final static String  PREFIX = "【DB切换拦截】";

    @Before("execution(* com.moyu.core..*.find*(..)) "+
            "|| execution(* com.moyu.core..*.get*(..)) "+
            "|| execution(* com.moyu.core..*.query*(..)) ")
    public void setReadDataSourceType() {
        //DynamicDataSourceHolder.setSlave();
        logger.info(PREFIX + "dataSource切换到：Read");
    }

    @Before("execution(* com.moyu.core..*.save*(..)) " +
            "|| execution(* com.moyu.core..*.change*(..))" +
            "|| execution(* com.moyu.core..*.remove*(..))")
    public void setWriteDataSourceType() {
        //DynamicDataSourceHolder.setMaster();
        logger.info(PREFIX + "dataSource切换到：write");
    }


}
