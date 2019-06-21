package com.moyu.apiweb.controller;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.moyu.core.user.domain.MyRole;
import com.moyu.core.user.domain.MyUser;
import com.moyu.core.user.service.LoginService;
import com.moyu.core.user.service.UserRoleRelationService;
import com.moyu.util.datasource.DynamicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @Auther: wishm
 * @Date: 2019/4/19 09:56
 * @Description:
 */
@RestController
@RequestMapping("/hello")
public class HelloWorld {

    @Autowired
    private UserRoleRelationService userRoleRelationService;

    @Autowired
    private Environment env;

    @Autowired
    private DynamicDataSource dynamicDataSource;

    /**
     * 添加数据源示例
     * 多数据源的配置表
     * @return
     */
    @GetMapping("/add_data_source")
    public Object addDataSource() {
        // 构建 DataSource 属性,
        Map<String, String> map = new HashMap<>();
        map.put(DruidDataSourceFactory.PROP_DRIVERCLASSNAME,
                env.getRequiredProperty("datasource.dbx.driverClassName"));
        map.put(DruidDataSourceFactory.PROP_URL,
                env.getRequiredProperty("datasource.dbx.url"));
        map.put(DruidDataSourceFactory.PROP_USERNAME,
                env.getRequiredProperty("datasource.dbx.username"));
        map.put(DruidDataSourceFactory.PROP_PASSWORD,
                env.getRequiredProperty("datasource.dbx.password"));
        map.put("database", "dynamic_db2");
        System.out.println("添加数据源："+dynamicDataSource.addDataSource(map));
        Set<Object> objects = DynamicDataSource.targetDataSources.keySet();
        objects.forEach(tab -> System.out.println(tab));

        return true;
    }
    //测试是否切换了数据源
    //切换 使用 查询渠道 queryChannel 例如 美期mq  迅喵xm 相对应的数据源名称
    @RequestMapping("/moyu/{dbName}")
    public String testSwitch(@PathVariable(name = "dbName") String dbName) {
        List<MyRole> moyu = userRoleRelationService.selectdRole(dbName,1);
        System.out.println(
                moyu
        );
        return "ss";
    }

    // volatile关键字会强制的保证线程的可见性
    private static boolean flag = true;

    private static void setI(boolean state) {
        System.out.println("线程设置了stop begin");
        flag = state; // 赋值 可见性测试，是否会使用高速缓存中的变量
        System.out.println("线程设置了stop end");
    }


    public static void main(String[] args) throws InterruptedException {


        Thread t1 = new Thread(() -> {
            System.out.println("进入方法" + flag);
            while (flag) {

                //System.out.println("方法执行");
                /**JVM会尽力保证内存的可见性 导致CPU的输出 耗时，CPU就有可能有时间去保证内存的可见性
                 *  public void println(String x) {
                 *         synchronized (this) {
                 *             print(x);
                 *             newLine();
                 *         }
                 *     }
                 */
            }
            System.out.println("线程结束");

        });

        t1.start();

        Thread.sleep(1000);
        setI(false);
    }
}
