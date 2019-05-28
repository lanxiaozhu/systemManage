package com.moyu.crawler.beike.controller;

import com.moyu.crawler.beike.domian.MoyuHouseInfo;
import com.moyu.crawler.beike.webCollector;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther: wishm
 * @Date: 2019/5/8 17:36
 * @Description:
 */
@RestController
public class se {

    @GetMapping("/get")
    public String get() throws Exception {
        /**
         * DemoAutoNewsCrawler 构造器中会进行 数据初始化，这两个参数接着会传给父类
         * super(crawlPath, autoParse);
         * crawlPath：表示设置保存爬取记录的文件夹，本例运行之后会在应用根目录下生成一个 "crawl" 目录存放爬取信息
         * */
        webCollector crawler = new webCollector("crawl", true);
        /**
         * 启动爬虫，爬取的深度为4层
         * 添加的第一层种子链接,为第1层
         */
        crawler.start(4);

        return "over";
    }
}
