package com.baixiang.spider.BtTianTang;

import com.baixiang.spider.pipeline.MoviePipeline;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.ConsolePipeline;
import us.codecraft.webmagic.processor.PageProcessor;

import static com.baixiang.spider.pipeline.MoviePipeline.MOVIE_INFO;
import static com.baixiang.spider.pipeline.MoviePipeline.MOVIE_POSTER;
import static com.baixiang.spider.pipeline.MoviePipeline.MOVIE_TITLE;

/**
 * Created by shenjj on 2017/6/19.
 */

@Component
@ComponentScan(value = "com.baixiang.spider")
public class BtTianTangProcessor implements PageProcessor {
    @Autowired
    private MoviePipeline moviePipeline;

    private Site site = Site.me().setRetryTimes(3).setSleepTime(1000).setTimeOut(10000);
    private Spider spider;

    public BtTianTangProcessor() {
    }

    @Override
    public Site getSite() {
        return site;
    }

    @Override
    public void process(Page page) {
//        page.addTargetRequests(page.getHtml().links().regex("(http://www\\.bttiantangs\\.com/movie/[\\w\\-]+/[\\w\\-]+)").all());
        page.addTargetRequests(page.getHtml().links().regex("http://www.bttiantangs.com/movie/\\d+.*").all());
//        page.addTargetRequests(page.getHtml().links().regex("http://www.bttiantangs.com/download/\\d+.*").all());
        page.putField(MOVIE_TITLE, page.getHtml().xpath("//div[@class='article_container']/h1/text()").toString());
        page.putField(MOVIE_INFO, page.getHtml().xpath("//div[@id='post_content']").toString());
        page.putField(MOVIE_POSTER, page.getHtml().xpath("//p[@class='tpic-cont-s']").css("img", "src").toString());
//        page.putField(MOVIE_INFO, page.getHtml().css("div#post_content").get());
        page.putField("torrent_content", page.getHtml().xpath("//div[@class='post_content']/p/text()").toString());
//        page.putField("content", page.getHtml().css("div.show-content").toString());
        if (page.getResultItems().get("movie_title") == null) {
            //skip this page
            page.setSkip(true);
        }
//        page.putField("readme", page.getHtml().xpath("//div[@id='readme']/tidyText()"));

    }


    public void start() {
        spider = Spider.create(this)
                .addPipeline(moviePipeline)
//                .addPipeline(new ConsolePipeline())
                .addUrl("http://www.bttiantangs.com/").thread(5);
        spider.run();
    }

    public void stop() {
        spider.stop();
    }
}
