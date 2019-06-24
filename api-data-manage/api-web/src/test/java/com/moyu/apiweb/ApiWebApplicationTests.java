package com.moyu.apiweb;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.moyu.apiweb.springBena.OrderConfig;
import com.moyu.apiweb.springBena.Teacher;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApiWebApplicationTests extends TestAb{



    @Test
    public void tests(){

    }





    @Test
    public void testBean() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(OrderConfig.class);
        Teacher rice = ac.getBean(Teacher.class);
        //resource  get全局搜搜我
    }

    @Test
    public void testEquals() {
       /* HashCodeHelper h = new HashCodeHelper();
        h.id =1;
        h.name="1";

        HashCodeHelper h1 = new HashCodeHelper();
        h1.id =1;
        h1.name="1";

        HashCodeHelper h2 = new HashCodeHelper();
        h2.id =2;
        h2.name="2";

        System.out.println(h.equals(h1));
        //结果是 false


        HashSet<HashCodeHelper> set = new HashSet();
        set.add(h);
        set.add(h1);
        set.add(h2);

        System.out.println(set.size());
        Iterator<HashCodeHelper> iterator = set.iterator();
        while(iterator.hasNext()){
            HashCodeHelper next = iterator.next();
            System.out.println(next);
        }*/
    }

}
