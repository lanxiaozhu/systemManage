package com.moyu.util.datasource;

import org.apache.commons.lang.StringUtils;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Auther: guoxianjun
 * @Date: 2018/12/3 22:32
 * @Description: 定义动态数据源，实现通过集成Spring提供的AbstractRoutingDataSource，
 * 只需要实现determineCurrentLookupKey方法即可
 */
public class DynamicDataSource extends AbstractRoutingDataSource {

    private AtomicInteger count = new AtomicInteger(0);

    @Override
    protected Object determineCurrentLookupKey() {
        String dataSourceKey = DynamicDataSourceHolder.getDataSourceKey();
        dataSourceKey = StringUtils.isEmpty(dataSourceKey) ? "master" : dataSourceKey;

        if (Objects.equals(DynamicDataSourceHolder.MASTER, dataSourceKey)) {
            return dataSourceKey;
        }
        // 读 简单负载均衡
        int number = count.getAndIncrement();
        if(isOdd(number)){
            System.out.println("----------------1----------------");
        }else {
            System.out.println("----------------0----------------");
        }

        System.out.println("currentDataSource-》》》》》》》》》" + dataSourceKey);
        return dataSourceKey;
    }


    public static boolean isOdd(int a) {
        System.out.println("原子数字值为："+a);
        if ((a & 1) == 1) {    //是奇数
            return true;
        }
        return false;
        //两个只要有一个是偶数就为等于0

        //两个都是奇数等于1
//        System.out.println(13&17);//1
//        System.out.println(12&17);//0
    }
}