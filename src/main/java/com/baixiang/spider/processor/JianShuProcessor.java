package com.baixiang.spider.processor;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

/**
 * Created by shenjj on 2017/6/16.
 */
public class JianShuProcessor implements PageProcessor {
    private Site site = Site.me().setRetryTimes(3).setSleepTime(1000).setTimeOut(10000);

    @Override
    public void process(Page page) {
//        page.addTargetRequests(page.getHtml().links().regex("(https://www\\.jianshu\\.com/[\\w\\-]+/[\\w\\-]+)").all());
        page.addTargetRequests(page.getHtml().links().regex("(http://www\\.jianshu\\.com/[\\w\\-]+/[\\w\\-]+)").all());
        page.addTargetRequests(page.getHtml().links().regex("http://www\\.jianshu\\.com/u/\\d+").all());
        page.putField("title", page.getHtml().css("h1.title").toString());
        page.putField("name", page.getHtml().css("a.name").toString());
//        page.putField("content", page.getHtml().css("div.show-content").toString());
        if (page.getResultItems().get("title")==null){
            //skip this page
            page.setSkip(true);
        }
//        page.putField("readme", page.getHtml().xpath("//div[@id='readme']/tidyText()"));

    }

    @Override
    public Site getSite() {
        return site;
    }

    public static void main(String[] args) {
        Spider.create(new JianShuProcessor()).addUrl("http://www.jianshu.com/").thread(5).run();
    }
}
