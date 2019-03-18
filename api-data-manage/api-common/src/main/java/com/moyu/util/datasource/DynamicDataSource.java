package com.moyu.util.datasource;

import org.apache.commons.lang.StringUtils;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * @Auther: guoxianjun
 * @Date: 2018/12/3 22:32
 * @Description: 定义动态数据源，实现通过集成Spring提供的AbstractRoutingDataSource，
 * 只需要实现determineCurrentLookupKey方法即可
 */
public class DynamicDataSource extends AbstractRoutingDataSource {

  /*  private final int dataSourceNumber;
    private AtomicInteger count = new AtomicInteger(0);

    public DynamicDataSource(int dataSourceNumber) {
        this.dataSourceNumber = dataSourceNumber;
    }*/


    @Override
    protected Object determineCurrentLookupKey() {
        String dataSourceKey = DynamicDataSourceHolder.getDataSourceKey();
     /*     if(dataSourceKey.equals(DatasourceType.write.getType())){
            return DatasourceType.write.getType();
        }
        // 读 简单负载均衡
        int number = count.getAndAdd(1);
        int lookupKey = number % dataSourceNumber;
        System.out.println("??"+lookupKey);
        return new Integer(lookupKey);*/
        // 使用DynamicDataSourceHolder保证线程安全，并且得到当前线程中的数据源key
        /*String dataSourceKey = DynamicDataSourceHolder.getDataSourceKey();
        if (dataSourceKey == null || dataSourceKey .equals( "master")) {
            return "master";
        }else {
            return "slave";
        }*/
        System.out.println("数据源-》》》》》》》》》" + dataSourceKey);
        return StringUtils.isEmpty(dataSourceKey) ? "master" : dataSourceKey;
    }
}