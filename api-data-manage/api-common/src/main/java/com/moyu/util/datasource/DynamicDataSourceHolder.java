package com.moyu.util.datasource;

/**
 * @Auther: guoxianjun
 * @Date: 2018/12/3 22:34
 * @Description: 个人就是按照单词意思理解： 动态数据源持有者  (其实是保存key者)
 */
public class DynamicDataSourceHolder {
    /* 主库 key*/
    public static final String MASTER ="master";
    /* 从库 key*/
    private static final String SLAVE = "slave";


    //ThreadLocal 当前线程变量
    private static final ThreadLocal<String> threadLocalHolder = new ThreadLocal<String>();
    /**
     * 设置数据源key
     * @param key
     */
    public static void putDataSourceKey(String key) {
        threadLocalHolder.set(key);
    }

    /**
     * 获取数据源key
     * @return
     */
    public static String getDataSourceKey() {
        return threadLocalHolder.get();
    }

    public static void setMaster() {
        putDataSourceKey(MASTER);
    }

    public static void setSlave() {
        putDataSourceKey(SLAVE);
    }

    public static void setX(String dbName) {
        putDataSourceKey(dbName);
    }
    /**
     * 删除数据源
     */
    public static void clearDataSourceKey() {
        threadLocalHolder.remove();
    }

    /**
     * 是否存在数据源
     * @param key
     * @return
     */
    public static boolean containDataSourceKey(String key) {
        return DynamicDataSource.targetDataSources.containsKey(key);
    }
}
