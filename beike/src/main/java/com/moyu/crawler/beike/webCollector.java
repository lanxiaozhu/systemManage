package com.moyu.crawler.beike;

import cn.edu.hfut.dmic.webcollector.model.CrawlDatums;
import cn.edu.hfut.dmic.webcollector.model.Page;
import cn.edu.hfut.dmic.webcollector.plugin.berkeley.BreadthCrawler;
import com.moyu.crawler.beike.helper.AnalysisHouseInfoFactory;
import com.moyu.crawler.beike.helper.BKHouseInfoService;
import com.moyu.crawler.beike.util.BeanUtil;


/**
 * @Auther: wishm
 * @Date: 2019/5/8 11:31
 * @Description: breadthCrawler 广度爬虫
 * BreadthCrawler 是 WebCollector 最常用的爬取器之一
 */
public class webCollector extends BreadthCrawler {


    static String url = "https://hz.ke.com/ershoufang/";

    public webCollector(String crawlPath, boolean autoParse) {
        super(crawlPath, autoParse);

        /**设置爬取的网站地址
         * addSeed 表示添加种子"https://www.meitulu.com/"
         * 种子链接会在爬虫启动之前加入到抓取信息中并标记为未抓取状态*/
        this.addSeed(url);
        /**
         * 循环添加了4个种子，其实就是分页，结果类似：
         * https://blog.github.com/page/2/
         * https://blog.github.com/page/3/
         * https://blog.github.com/page/4/
         * https://blog.github.com/page/5/
         */
        /*for (int pageIndex = 2; pageIndex <= 5; pageIndex++) {
            String seedUrl = String.format("https://hz.ke.com/ershoufang/pg%d/", pageIndex);
            this.addSeed(seedUrl);
        }*/

        /** addRegex 参数为一个 url 正则表达式, 可以用于过滤不必抓取的链接，如 .js .jpg .css ... 等
         * 也可以指定抓取某些规则的链接，如下 addRegex 中会抓取 此类地址：
         * https://blog.github.com/2018-07-13-graphql-for-octokit/
         * */
        //this.addRegex("https://blog.github.com/[0-9]{4}-[0-9]{2}-[0-9]{2}-[^/]+/");
        /**
         * 过滤 jpg|png|gif 等图片地址 时：
         * this.addRegex("-.*\\.(jpg|png|gif).*");
         * 过滤 链接值为 "#" 的地址时：
         * this.addRegex("-.*#.*");
         */

        /**设置线程数*/
        setThreads(1);
        /**
         * 设置爬取URL数量的上限
         */
        setTopN(1);

        /**
         * 是否进行断电爬取，默认为 false
         * setResumable(true);
         */

    }

    /**
     * 必须重写 visit 方法，作用是:
     * 在整个抓取过程中,只要抓到符合要求的页面,webCollector 就会回调该方法,并传入一个包含了页面所有信息的 page 对象
     *
     * @param page
     * @param
     */
    public void visit(Page page, CrawlDatums crawlDatums) {
        /**如果此页面地址 确实是要求爬取网址，则进行取值
         */
        //if (page.matchUrl("https://blog.github.com/[0-9]{4}-[0-9]{2}-[0-9]{2}[^/]+/")) {
        BKHouseInfoService bKHouseInfoService = (BKHouseInfoService)BeanUtil.getBean("bKHouseInfoService");
        bKHouseInfoService.getHouseDetail(page);

    }

}


