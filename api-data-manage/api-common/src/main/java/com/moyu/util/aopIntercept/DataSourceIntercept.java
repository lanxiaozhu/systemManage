package com.moyu.util.aopIntercept;

import com.moyu.util.MultipleData;
import com.moyu.util.datasource.DynamicDataSourceHolder;
import org.apache.commons.lang.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Objects;

/**
 * @Auther: guoxianjun
 * @Date: 2019/1/24 19:07
 * @Description: 拦截数据操作
 */
@Aspect
@Component
public class DataSourceIntercept {

    private final static Logger logger = LoggerFactory.getLogger(DataSourceIntercept.class);
    private final static String PREFIX = "【DB切换拦截】";

    //中期redis 中获取 或者 使用搜索引擎 模糊匹配关键字
    //后期图标展示 热词 定义方法名
    String[] read = {"read", "search", "find", "get", "query", "select", "exist"};

    /**
     * 拦截所有dao 层操作，包含 查询的方法名 全部切换到只读库
     */
    @Before("execution(* com.moyu.core..*.dao..*(..)) ")
    public void setReadDataSourceType(JoinPoint joinPoint) {
        Method method = printIntercept(joinPoint);

        if(judgeNullVal(joinPoint, method)) return;

        readChannel(joinPoint);
    }

    private void readChannel(JoinPoint joinPoint) {
        boolean switchStats = isReader(joinPoint.getSignature().getName());
        //符合只读库路由标准
        if (switchStats) {
            DynamicDataSourceHolder.setSlave();
            logger.info(PREFIX + "dataSource切换到：Read");
        } else {
            DynamicDataSourceHolder.setMaster();
            logger.info(PREFIX + "dataSource切换到：write");
        }
    }

    //字符串是否以XXX开头
    private boolean isReader(String methodName) {
        return StringUtils.startsWithAny(methodName, read);
    }

    /**
     * 空值判断
     *
     * @param joinPoint
     * @param method
     */
    private boolean judgeNullVal(JoinPoint joinPoint, Method method) {
        //1-判断 对象不能为空
        if (method == null) return false;

        //2-判断 注解是否存在
        if (!method.isAnnotationPresent(MultipleData.class)) return false;

        Object[] params = joinPoint.getArgs();
        Object param = params[0];
        if (Objects.isNull(param)) return false;

        return switchDataSource(param);
    }

    /**
     * 数据源切换
     *
     * @param param
     * @return
     */
    private boolean switchDataSource(Object param) {
        if (param instanceof String) {
            String dbName = param.toString();
            if (DynamicDataSourceHolder.containDataSourceKey(dbName) && !dbName.equals(DynamicDataSourceHolder.getDataSourceKey())) {
                DynamicDataSourceHolder.setX(dbName);
                logger.info("切换到多数据源:" , dbName);
                return true;
            }
        }
        return false;
    }

    /**
     * 输出本次拦截信息
     *
     * @param joinPoint
     * @return
     */
    private Method printIntercept(JoinPoint joinPoint) {
        logger.info("intercept Method:" , joinPoint.getSignature().getName());
        logger.info("intercept SimpleClassName:" , joinPoint.getSignature().getDeclaringType().getSimpleName());
        logger.info("intercept ClassPathName:" , joinPoint.getSignature().getDeclaringTypeName());
        //获取传入目标方法的参数
        Object[] args = joinPoint.getArgs();
        for (int i = 0; i < args.length; i++) {
            logger.info("第" + (i + 1) + "个参数为:" + args[i]);
        }
        logger.info("被代理的对象:" , joinPoint.getTarget());
        logger.info("代理对象自己:" , joinPoint.getThis());

        Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        Method method = methodSignature.getMethod();
        return method;
    }
}
