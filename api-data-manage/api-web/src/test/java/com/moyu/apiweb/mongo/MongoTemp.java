package com.moyu.apiweb.mongo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Calendar;
import java.util.Date;

/**
 * @Auther: wishm
 * @Date: 2019/6/11 23:01
 * @Description:
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class MongoTemp {

    @Test
    public void tests(){

    }

    /**
     * 获得当前日期
     *
     * @return
     */
    public static Date getNow() {
        Calendar cal = Calendar.getInstance();
        Date currDate = cal.getTime();
        return currDate;
    }

}
