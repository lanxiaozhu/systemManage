package com.moyu.apiweb.controller;

import com.moyu.core.user.domain.MyUser;
import com.moyu.core.user.service.LoginService;
import com.moyu.core.user.service.RoleMenuRelationService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther: wishm
 * @Date: 2019/4/19 09:56
 * @Description:
 */
@RestController
@RequestMapping("/hello")
public class HelloWorld {

    @Autowired
    private LoginService loginService;


    @RequestMapping("/moyu")
    public String testSwitch() {
        MyUser moyu = loginService.getUser("moyu");

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
