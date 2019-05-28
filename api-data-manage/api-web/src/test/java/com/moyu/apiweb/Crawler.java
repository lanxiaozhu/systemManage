package com.moyu.apiweb;

import cn.edu.hfut.dmic.webcollector.model.CrawlDatums;
import cn.edu.hfut.dmic.webcollector.model.Page;
import cn.edu.hfut.dmic.webcollector.plugin.berkeley.BreadthCrawler;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * @Auther: wishm
 * @Date: 2019/4/22 16:22
 * @Description: 爬虫
 */
public class Crawler extends BreadthCrawler {


    public Crawler(String crawlPath, boolean autoParse) {
        super(crawlPath, autoParse);

        /**设置爬取的网站地址
         * addSeed 表示添加种子
         * 种子链接会在爬虫启动之前加入到抓取信息中并标记为未抓取状态.这个过程称为注入*/
        this.addSeed("http://www.dxblog.cn/");


        /**
         * 循环添加了4个种子，其实就是分页，结果类似：
         * https://blog.github.com/page/2/
         * https://blog.github.com/page/3/
         * https://blog.github.com/page/4/
         * https://blog.github.com/page/5/
         */
        for (int pageIndex = 2; pageIndex <= 5; pageIndex++) {
            String seedUrl = String.format("http://www.dxblog.cn/page/%d", pageIndex);
            this.addSeed(seedUrl);
        }


        /** addRegex 参数为一个 url 正则表达式, 可以用于过滤不必抓取的链接，如 .js .jpg .css ... 等
         * 也可以指定抓取某些规则的链接，如下 addRegex 中会抓取 此类地址：
         * https://blog.github.com/2018-07-13-graphql-for-octokit/
         * */
        /*正规则，待爬取网页至少符合一条正规则，才可以爬取*/
        // crawler.addRegex("+^http://www.xinhuanet.com/");
        this.addRegex("http://www.dxblog.cn/page/[0-9]{4}-[0-9]{2}-[0-9]{2}-[^/]+/");
        /**
         * 过滤 jpg|png|gif 等图片地址 时：
         * 负规则，只要符合一条负规则，跳过，不爬取
         * crawler.addRegex("-^http://news.xinhuanet.com/edu.*");
         * this.addRegex("-.*\\.(jpg|png|gif).*");
         * 过滤 链接值为 "#" 的地址时：
         * this.addRegex("-.*#.*");
         */

        /**
         * 是否进行断电爬取，默认为 false
         * setResumable(true);
         */

        /*设置User-Agent*/
//        crawler.setUseragent("Mozilla/5.0 (X11; Ubuntu; Linux i686; rv:26.0) Gecko/20100101 Firefox/26.0");
        /*设置cookie*/
//       crawler.setCookie("你的Cookie");

        /*可以设置每个线程visit的间隔，这里是毫秒*/
        //crawler.setVisitInterval(1000);
        /*可以设置http请求重试的间隔，这里是毫秒*/
        //crawler.setRetryInterval(1000);

    }

    /**
     * breadth 宽度;(知识、兴趣等的)广泛    大面积爬虫
     */

    public static void main(String[] args) throws Exception {
        /**
         * Crawler 构造器中会进行 数据初始化，这两个参数接着会传给父类
         * super(crawlPath, autoParse);
         * crawlPath：表示设置保存爬取记录的文件夹，本例运行之后会在应用根目录下生成一个 "crawl" 目录存放爬取信息
         * */
        Crawler crawler = new Crawler("crawl", true);
        //线程数
        crawler.setThreads(50);
        //设置递归爬去时 每个页面产生的url数量
       crawler.setTopN(100);

        /*设置爬虫是否为断点爬取*/
        //crawler.setResumable(false);
        /*设置保存爬取记录的文件夹*/
        //crawler.setCrawlPath("crawl_hfut");

        /*设置爬虫是否以断点模式爬取
        如果设置为true，爬虫会在启动时继续以前的任务爬取
        默认为false，如果为false，每次启动爬虫都会重新爬取*/
        //crawler.setResumable(true);

        /**
         * 启动爬虫，爬取的深度为4层
         * 添加的第一层种子链接,为第1层
         * 这个5层是这么理解: 当只添加了一个种子, 抓这个种子链接为第1层。
         * 解析种子链接页面跟据正则过滤想要的链接保存至待抓取记录. 那么第2层就是抓取1层保存的记录并解析保存新记录,依次类推.
         */
        crawler.start(4);

    }

    /**
     * 必须重写 visit 方法，作用是:
     * 在整个抓取过程中,只要抓到符合要求的页面,webCollector 就会回调该方法,并传入一个包含了页面所有信息的 page 对象
     *
     * @param page
     * @param crawlDatums
     */
    @Override
    public void visit(Page page, CrawlDatums crawlDatums) {
        String url = page.url();
        /**如果此页面地址 确实是要求爬取网址，则进行取值
         */
        if (page.matchUrl("http://www.dxblog.cn/page/[0-9]")) {

            System.out.println("URL:" + url);
            /**
             * 通过 选择器 获取页面 标题以及 正文内容
             * */
            Elements article = page.select("article[class=post_box]");
            for (int i = 0; i <article.size() ; i++) {
                //标题内容
                Element element = article.get(i);

                Elements elementsByClass = element.getElementsByClass("entry-title");
                System.out.println("title:" + elementsByClass.text());
                //标题链接
                Elements a = elementsByClass.select("a");
                System.out.println("link:"+a.attr("href"));
                //阅览量
                String readCount = element.select("span[class=mu-ml-eye]").text();
                System.out.println("readCount:"+readCount);
            }
            System.out.println("----------------------------------------");

        }
    }
}
