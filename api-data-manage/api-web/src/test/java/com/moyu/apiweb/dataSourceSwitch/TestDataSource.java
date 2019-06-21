package com.moyu.apiweb.dataSourceSwitch;

import com.moyu.core.user.domain.MyUser;
import com.moyu.core.user.service.LoginService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Auther: wishm
 * @Date: 2019/6/13 15:43
 * @Description: 测试数据源切换主从
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestDataSource {


    @Autowired
    private LoginService loginService;
    @Test
    public void testSwitch(){
        MyUser moyu = loginService.getUser( "moyu");

        System.out.println(
                moyu
        );

    }
}
