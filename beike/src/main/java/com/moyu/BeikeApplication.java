package com.moyu;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@MapperScan("com.moyu.crawler.beike.dao")//扫描Mapper
@SpringBootApplication
public class BeikeApplication {

    public static void main(String[] args) {
        SpringApplication.run(BeikeApplication.class, args);
    }

}
