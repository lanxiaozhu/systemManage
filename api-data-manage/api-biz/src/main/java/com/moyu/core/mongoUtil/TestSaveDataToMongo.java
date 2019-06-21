package com.moyu.core.mongoUtil;

import static org.springframework.jmx.support.ObjectNameManager.getInstance;

/**
 * @Auther: wishm
 * @Date: 2019/6/21 23:27
 * @Description:
 */
public class TestSaveDataToMongo {
    public static void main(String[] args) {
        //结构化数据 封装
        TestUser user = new TestUser();
        user.setName("我是谁");


        //模板数据组装
        MBasicAttr instance = new MBasicAttr<TestUser>().rsThis(user);


        TestUser users= (TestUser) instance.getBasic();

        System.out.println(users.toString());



    }
}
