package com.baixiang.spider;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

/**
 * Created by shenjj on 2017/6/19.
 */
public class BtTianTangProcessor implements PageProcessor {
    private Site site = Site.me().setRetryTimes(3).setSleepTime(1000).setTimeOut(10000);
    private static Spider spider = Spider.create(new BtTianTangProcessor()).addUrl("http://www.bttiantangs.com/").thread(5);

    @Override
    public Site getSite() {
        return site;
    }

    @Override
    public void process(Page page) {
//        page.addTargetRequests(page.getHtml().links().regex("(http://www\\.bttiantangs\\.com/movie/[\\w\\-]+/[\\w\\-]+)").all());
        page.addTargetRequests(page.getHtml().links().regex("http://www.bttiantangs.com/movie/\\d+.*").all());
        page.addTargetRequests(page.getHtml().links().regex("http://www.bttiantangs.com/download/\\d+.*").all());
        page.putField("movie_title", page.getHtml().xpath("//div[@class='article_container']/h1/text()").toString());
        page.putField("torrent_content", page.getHtml().xpath("//div[@class='post_content']/p/text()").toString());
//        page.putField("content", page.getHtml().css("div.show-content").toString());
        if (page.getResultItems().get("movie_title") == null) {
            //skip this page
            page.setSkip(true);
        }
//        page.putField("readme", page.getHtml().xpath("//div[@id='readme']/tidyText()"));

    }

    public static void main(String[] args) {
        spider.run();
    }

    public static void start() {
        spider.run();
    }

    public static void stop() {
        spider.stop();
    }
}
