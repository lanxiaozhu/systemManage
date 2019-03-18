package com.moyu.apiweb.apiweb;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;
import java.util.stream.Collectors;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApiWebApplicationTests {
    @Test
    public void test1(){
        List<Integer> list1=new ArrayList<>();
        list1.add(1);
        list1.add(2);
        list1.add(3);

        List<Integer> list2=new ArrayList<>();
        list2.add(3);
        list2.add(4);
        list2.add(5);

        System.out.println("====求交集===");

        List<Integer> list=list1.stream().filter(t->list2.contains(t)).collect(Collectors.toList());
        list.stream().forEach(System.out::println);




        System.out.println("====求差集===");
        list=list1.stream().filter(t-> !list2.contains(t)).collect(Collectors.toList());
        list.stream().forEach(System.out::println);


        System.out.println("====求并集===");

        list.addAll(list1);
        list.addAll(list2);
        list=list.stream().distinct().collect(Collectors.toList());
        list.stream().forEach(System.out::println);
    }
    @Test
    public void contextLoads() {
        Set<String> sets = new HashSet<>();
        sets.add("哥哥");
        sets.add("爸爸");
        sets.add("伯伯");

        Set<String> setr = new HashSet<>();
        sets.add("中兴");
        sets.add("银行");

        String rs = "XXX爸爸";

        //对比 是否 包含 亲属！true
        //过滤亲属
       sets.stream().filter(item -> {
            if(item.contains(rs)){
                System.out.println("亲属"+rs);
            }
            return true;
        }).collect(Collectors.toSet());
       //过滤掉分控
        setr.stream().filter(item -> {
            if(rs.contains(item)){
                System.out.println("风控"+rs);
            }
            return true;
        }).collect(Collectors.toSet());
        //剩余普通

    }

}
