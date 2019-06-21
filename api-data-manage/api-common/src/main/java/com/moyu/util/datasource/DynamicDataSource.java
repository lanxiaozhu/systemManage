package com.moyu.util.datasource;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Auther: guoxianjun
 * @Date: 2018/12/3 22:32
 * @Description: 定义动态数据源，实现通过集成Spring提供的AbstractRoutingDataSource，
 * 只需要实现determineCurrentLookupKey方法即可
 */
public class DynamicDataSource extends AbstractRoutingDataSource {

    private final Logger logger = LoggerFactory.getLogger(getClass());

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
        if (isOdd(number)) {
            System.out.println("----------------1----------------");
        } else {
            System.out.println("----------------0----------------");
        }

        System.out.println("currentDataSource-》》》》》》》》》" + dataSourceKey);
        return dataSourceKey;
    }

    public static Map<Object, Object> targetDataSources = new HashMap<>();

    @Override
    public void setTargetDataSources(Map<Object, Object> targetDataSources) {
        super.setTargetDataSources(targetDataSources);
        DynamicDataSource.targetDataSources = targetDataSources;
    }

    /**
     * 是否存在当前key的 DataSource
     *
     * @param key
     * @return 存在返回 true, 不存在返回 false
     */
    public static boolean isExistDataSource(String key) {
        return targetDataSources.containsKey(key);
    }

    /**
     * 动态增加数据源
     *
     * @param map 数据源属性
     * @return
     */
    public synchronized boolean addDataSource(Map<String, String> map) {
        try {
            Connection connection = null;
            // 排除连接不上的错误
            try {
                Class.forName(map.get(DruidDataSourceFactory.PROP_DRIVERCLASSNAME));
                connection = DriverManager.getConnection(
                        map.get(DruidDataSourceFactory.PROP_URL),
                        map.get(DruidDataSourceFactory.PROP_USERNAME),
                        map.get(DruidDataSourceFactory.PROP_PASSWORD));

                System.out.println(connection.isClosed());
            } catch (Exception e) {
                return false;
            } finally {
                if (connection != null && !connection.isClosed())
                    connection.close();
            }

            String database = map.get("database");//获取要添加的数据库名
            if (StringUtils.isBlank(database)) return false;
            if (DynamicDataSource.isExistDataSource(database)) return true;
            DruidDataSource druidDataSource = (DruidDataSource) DruidDataSourceFactory.createDataSource(map);
            druidDataSource.init();
            Map<Object, Object> targetMap = DynamicDataSource.targetDataSources;
            targetMap.put(database, druidDataSource);
            // 当前 targetDataSources 与 父类 targetDataSources 为同一对象 所以不需要set
//   this.setTargetDataSources(targetMap);
            this.afterPropertiesSet();
            logger.info("dataSource {} has been added", database);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return false;
        }
        return true;
    }//https://www.jb51.net/article/147300.htm

    public static boolean isOdd(int a) {
        System.out.println("原子数字值为：" + a);
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