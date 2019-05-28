package com.moyu.apiweb;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @Auther: wishm
 * @Date: 2019/4/13 09:52
 * @Description:
 */
public class ExecutorServer {
    public static void main(String[] args) {
        Date time = Calendar.getInstance().getTime();
        // Single();
        //Scheduled(time);
        //CaChed(time);
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(3);
        for (int i = 0; i < 10; i++) {
            fixedThreadPool.execute(() -> {
                try {
                    System.out.println(Calendar.getInstance().getTime() + "；当前线程名称" + Thread.currentThread().getName());
                    Thread.sleep(3000);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
        /*
        Sat Apr 13 11:03:37 CST 2019；当前线程名称pool-1-thread-3
        Sat Apr 13 11:03:37 CST 2019；当前线程名称pool-1-thread-2
        Sat Apr 13 11:03:37 CST 2019；当前线程名称pool-1-thread-1


        Sat Apr 13 11:03:40 CST 2019；当前线程名称pool-1-thread-3
        Sat Apr 13 11:03:40 CST 2019；当前线程名称pool-1-thread-2
        Sat Apr 13 11:03:40 CST 2019；当前线程名称pool-1-thread-1


        Sat Apr 13 11:03:43 CST 2019；当前线程名称pool-1-thread-3
        Sat Apr 13 11:03:43 CST 2019；当前线程名称pool-1-thread-2
        Sat Apr 13 11:03:43 CST 2019；当前线程名称pool-1-thread-1


        Sat Apr 13 11:03:46 CST 2019；当前线程名称pool-1-thread-3

         */
    }

    private static void CaChed(Date time) {
        System.out.println(time);//开始时间
        //可缓存的线程池 核心线程数无限大，所以即使线程输出完成 休眠十秒，十个线程也是在一瞬间执行完成的
        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
        for (int i = 0; i < 10; i++) {
            final int index = i;

            cachedThreadPool.execute(() -> {

                System.out.println(Thread.currentThread().getName() + ":" + index);
                System.out.println("三秒后执行，每次间隔两秒" + Calendar.getInstance().getTime() + "；当前线程名称" + Thread.currentThread().getName());
                try {
                    Thread.sleep(10000);//休眠十秒
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });

        }
    }

    private static void Scheduled(Date time) {
        System.out.println(time);

        //定时线程  核心线程数量 2
        ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(2);
        for (int i = 0; i < 5; i++) {
            scheduledThreadPool.schedule(() -> {
                System.out.println("三秒后执行，每次间隔两秒" + Calendar.getInstance().getTime() + "；当前线程名称" + Thread.currentThread().getName());
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }, 3, TimeUnit.SECONDS);
        }
        /*
        Sat Apr 13 10:28:51 CST 2019
        三秒后执行，每次间隔两秒Sat Apr 13 10:28:54 CST 2019；当前线程名称pool-1-thread-1
        三秒后执行，每次间隔两秒Sat Apr 13 10:28:54 CST 2019；当前线程名称pool-1-thread-2
        三秒后执行，每次间隔两秒Sat Apr 13 10:28:56 CST 2019；当前线程名称pool-1-thread-1
        三秒后执行，每次间隔两秒Sat Apr 13 10:28:56 CST 2019；当前线程名称pool-1-thread-2
        三秒后执行，每次间隔两秒Sat Apr 13 10:28:58 CST 2019；当前线程名称pool-1-thread-2
         */
    }

    private static void Single() {
        //单例的线程
        ExecutorService service = Executors.newSingleThreadExecutor();

        for (int i = 0; i < 5; i++) {
            service.execute(() -> {
                System.out.println(Thread.currentThread().getName() + " : " + "查看线程是否是单例的");
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
    }
}
