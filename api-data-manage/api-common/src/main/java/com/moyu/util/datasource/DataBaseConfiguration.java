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
 * @Description: 注入主从
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
     *
     *
     * @Primary
     Description:

     Parameter 0 of method sqlSessionFactory in org.mybatis.spring.boot.autoconfigure.MybatisAutoConfiguration required a single bean, but 3 were found:
     - master: defined by method 'writeDataSource' in class path resource [com/moyu/util/datasource/DataBaseConfiguration.class]
     - slave: defined by method 'readDataSourceOne' in class path resource [com/moyu/util/datasource/DataBaseConfiguration.class]
     - roundRobinDataSouceProxy: defined by method 'roundRobinDataSouceProxy' in class path resource [com/moyu/util/datasource/DataBaseConfiguration.class]
     Action:

     Consider marking one of the beans as @Primary, updating the consumer to accept multiple beans, or using @Qualifier to identify the bean that should be consumed

     * @return
     */
    @Primary
    @Bean
    public AbstractRoutingDataSource roundRobinDataSouceProxy() {
        DynamicDataSource proxy = new DynamicDataSource();
        proxy.setDefaultTargetDataSource(writeDataSource());


        Map<Object, Object> targetDataSources = new HashMap<Object, Object>();
        // DataSource writeDataSource = SpringContextHolder.getBean("writeDataSource");
        // 写
        targetDataSources.put("master", writeDataSource());
        targetDataSources.put("slave", readDataSourceOne());
        // targetDataSources.put(DataSourceType.read.getType(),readDataSource);
        //多个读数据库时
       /* for (int i = 0; i < size; i++) {
            targetDataSources.put(i, readDataSources.get(i));
        }*/
        //设置默认
        proxy.setTargetDataSources(targetDataSources);
        return proxy;
    }

    /**
     * 配置@Transactional注解事物
     * @return
     */
    @Bean
    public PlatformTransactionManager transactionManager() {
        return new DataSourceTransactionManager(roundRobinDataSouceProxy());
    }
}