package com.moyu.apiweb.apiweb;

import com.moyu.apiweb.apiweb.springBena.OrderConfig;
import com.moyu.apiweb.apiweb.springBena.Teacher;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;
import java.util.stream.Collectors;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApiWebApplicationTests {
    @Test
    public void test1() {
        List<Integer> list1 = new ArrayList<>();
        list1.add(1);
        list1.add(2);
        list1.add(3);

        List<Integer> list2 = new ArrayList<>();
        list2.add(3);
        list2.add(4);
        list2.add(5);

        System.out.println("====求交集===");

        List<Integer> list = list1.stream().filter(t -> list2.contains(t)).collect(Collectors.toList());
        list.stream().forEach(System.out::println);


        System.out.println("====求差集===");
        list = list1.stream().filter(t -> !list2.contains(t)).collect(Collectors.toList());
        list.stream().forEach(System.out::println);


        System.out.println("====求并集===");

        list.addAll(list1);
        list.addAll(list2);
        list = list.stream().distinct().collect(Collectors.toList());
        list.stream().forEach(System.out::println);
    }

    @Test
    public void contextLoads() {

        Map map = new HashMap();

        map.put("1","1");
        map.put("2","1");
        map.put("3","1");
        map.put("4","1");
        map.put("5","1");
        map.put("6","1");
        map.put("7","1");
        map.put("8","1");
        map.put("9","10");
        map.put("10","1");
        map.put("11","1");
        map.put("12","1");
        map.put("13","1");
        map.put("14","1");



    }

    @Test
    public void testBean(){
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(OrderConfig.class);
        Teacher rice = ac.getBean(Teacher.class);
        //resource  get全局搜搜我
    }
}
