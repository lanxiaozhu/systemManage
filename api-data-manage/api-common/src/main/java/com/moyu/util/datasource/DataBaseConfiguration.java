package com.moyu.util.datasource;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Auther: guoxianjun
 * @Date: 2018/12/4 10:26
 * @Description: 初始化启动  注入主从
 */
@Configuration
public class DataBaseConfiguration {
    private final static Logger logger = LoggerFactory.getLogger(DataBaseConfiguration.class);
    private final static String  PREFIX = "【注入主从】";

    @Bean(name = "master")
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource writeDataSource() {
        logger.info(PREFIX + "-------------------- writeDataSource init ---------------------");
        return DataSourceBuilder.create().build();
    }

    /**
     * 有多少个从库就要配置多少个
     *
     * @return
     */
    @Bean(name = "slave")
    @ConfigurationProperties(prefix = "spring.slave")
    public DataSource readDataSourceOne() {
        logger.info(PREFIX + "-------------------- readDataSourceOne init ---------------------");
        return DataSourceBuilder.create().build();
    }

    /**
     * Dynamic data source.
     *
     * @return the data source
     */
    @Primary
    @Bean
    public AbstractRoutingDataSource roundRobinDataSouceProxy() {
        DynamicDataSource routing = new DynamicDataSource();
        //设置默认的目标数据库
        routing.setDefaultTargetDataSource(writeDataSource());


        Map<Object, Object> targetDataSources = new HashMap<Object, Object>();
        targetDataSources.put("master", writeDataSource());
        targetDataSources.put("slave", readDataSourceOne());

        //设置多数据源
        routing.setTargetDataSources(targetDataSources);
        return routing;
    }

    /**
     * 配置@Transactional注解事物
     * @return
     */
    @Bean
    public PlatformTransactionManager transactionManager() {
        logger.info(PREFIX + "-------------------- annotation transactional init ---------------------");
        return new DataSourceTransactionManager(roundRobinDataSouceProxy());
    }
}