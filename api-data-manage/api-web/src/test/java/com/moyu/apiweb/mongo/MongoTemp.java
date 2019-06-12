package com.moyu.apiweb.mongo;

import com.moyu.core.mongoUtil.MBasicAttr;
import com.moyu.core.mongoUtil.MongodbUtils;
import com.moyu.core.mongoUtil.TestUser;
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
    public void tests() throws IllegalAccessException, InstantiationException {
/*
        TestUser ts = new TestUser();
        ts.setId(99);
        ts.setName("gxj");
        ts.setAge("24");

        //封装时 结果
        MBasicAttr c = MBasicAttr.getInstance(ts);

        //结果 操作
        MongodbUtils.save(c,"CollectionName");*/
        MBasicAttr mBasicAttr = MBasicAttr.class.newInstance();
        MBasicAttr mBasicAttr1 = new MBasicAttr();
        System.out.println(mBasicAttr.equals(mBasicAttr1));



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
