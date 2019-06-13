package com.moyu.util.aopIntercept;

import com.moyu.util.datasource.DynamicDataSourceHolder;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

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
    private final static String DAO = "dao";

    //中期redis 中获取 或者 使用搜索引擎 模糊匹配关键字
    //后期图标展示 热词 定义方法名
    String[] read = {"read", "search", "find", "get", "query", "select"};

    /**
     * 第一个* 返回值类型任意
     * com.XX.XXX.XX 切入包名
     * .. 表示当前包 及 子包
     * 第二个* 类名称 ，* 代表所有类
     * .find 代表某些方法名 ，可以写为 .* 即所有方法
     * (..) 任何参数
     */


    /**
     * 拦截所有dao 层操作，包含 查询的方法名 全部切换到只读库
     *
     * @param joinPoint
     */
    @Before("execution(* com.moyu.core..*.*(..)) ")
    public void setReadDataSourceType(JoinPoint joinPoint) {
        if (joinPoint.getSignature().getDeclaringTypeName().contains(DAO)) {
            classInfo(joinPoint);
        }
    }


//    @Before("execution(* com.moyu.core..*.find*(..)) " +
//            "|| execution(* com.moyu.core..*.get*(..)) " +
//            "|| execution(* com.moyu.core..*.query*(..)) " +
//            "|| execution(* com.moyu.core..*.select*(..)) ")
//    public void setReadDataSourceType(JoinPoint joinPoint) {
//        if (joinPoint.getSignature().getDeclaringTypeName().contains(DAO)) {
//            classInfo(joinPoint);
//            DynamicDataSourceHolder.setSlave();
//            logger.info(PREFIX + "dataSource切换到：Read");
//        }
//    }
//
//    @Before("execution(* com.moyu.core..*.save*(..)) " +
//            "|| execution(* com.moyu.core..*.insert*(..))"+
//
//            "|| execution(* com.moyu.core..*.delete*(..))"+
//            "|| execution(* com.moyu.core..*.remove*(..))" +
//
//            "|| execution(* com.moyu.core..*.change*(..))" +
//            "|| execution(* com.moyu.core..*.update*(..))" +
//            "|| execution(* com.moyu.core..*.edit*(..))")
//    public void setWriteDataSourceType(JoinPoint joinPoint) {
//        if (joinPoint.getSignature().getDeclaringTypeName().contains(DAO)) {
//            classInfo(joinPoint);
//            DynamicDataSourceHolder.setMaster();
//            logger.info(PREFIX + "dataSource切换到：write");
//        }
//    }

    private void classInfo(JoinPoint joinPoint) {

        /**/
        printIntercept(joinPoint);
        boolean switchStats = false;
        for (String str : read) {
            if (joinPoint.getSignature().getName().contains(str)) {
                switchStats=true;
            }
        }


        if (switchStats) {
            DynamicDataSourceHolder.setSlave();
            logger.info(PREFIX + "dataSource切换到：Read");
        } else {
            DynamicDataSourceHolder.setMaster();
            logger.info(PREFIX + "dataSource切换到：write");
        }


    }


    private void printIntercept(JoinPoint joinPoint) {
        System.out.println("intercept Method:" + joinPoint.getSignature().getName());
        System.out.println("intercept SimpleClassName:" + joinPoint.getSignature().getDeclaringType().getSimpleName());
        System.out.println("intercept ClassPathName:" + joinPoint.getSignature().getDeclaringTypeName());
        //获取传入目标方法的参数
        Object[] args = joinPoint.getArgs();
        for (int i = 0; i < args.length; i++) {
            System.out.println("第" + (i + 1) + "个参数为:" + args[i]);
        }
        System.out.println("被代理的对象:" + joinPoint.getTarget());
        System.out.println("代理对象自己:" + joinPoint.getThis());

    }
}
