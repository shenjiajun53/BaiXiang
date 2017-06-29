package com.baixiang.spider.BtTianTang;

import com.baixiang.spider.pipeline.MoviePipeline;
import com.baixiang.spider.pipeline.TorrentPipeline;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Selectable;

import java.util.List;

import static com.baixiang.spider.pipeline.MoviePipeline.MOVIE_TITLE;
import static com.baixiang.spider.pipeline.TorrentPipeline.MAGNET_URL;
import static com.baixiang.spider.pipeline.TorrentPipeline.TORRENT_NAME;

/**
 * Created by shenjj on 2017/6/29.
 */

@Component
@ComponentScan(value = "com.baixiang.spider")
public class BtTianTangTorrentProcessor implements PageProcessor {
    private static final Logger logger = LoggerFactory.getLogger(BtTianTangTorrentProcessor.class);
    @Autowired
    private TorrentPipeline torrentPipeline;

    private Site site = Site.me().setRetryTimes(3).setSleepTime(1000).setTimeOut(10000);
    private Spider spider;

    public BtTianTangTorrentProcessor() {
    }

    @Override
    public Site getSite() {
        return site;
    }

    @Override
    public void process(Page page) {
        page.addTargetRequests(page.getHtml().links().regex("http://www.bttiantangs.com/movie/\\d+.*").all());
        page.addTargetRequests(page.getHtml().links().regex("http://www.bttiantangs.com/download/\\d+.*").all());


        page.putField(MOVIE_TITLE, page.getHtml().xpath("//div[@class='article_container']/h1/a/text()").toString());
//        page.putField(TORRENT_NAME, page.getHtml().xpath("//div[@class='post_content']/p/text()").toString());
        page.putField(MAGNET_URL, page.getHtml().xpath("//span[@id='link_text_span']/p/text()").toString());
        List<Selectable> contentNodes = page.getHtml().xpath("//span[@id='link_text_span']/p").nodes();
        for (int i = 0; i < contentNodes.size(); i++) {
            logger.info("node=" + contentNodes.get(i).toString());
        }

        if (page.getResultItems().get(MOVIE_TITLE) == null) {
            //skip this page
            page.setSkip(true);
        }
//        page.putField("readme", page.getHtml().xpath("//div[@id='readme']/tidyText()"));

    }


    public void start() {
        spider = Spider.create(this)
                .addPipeline(torrentPipeline)
//                .addPipeline(new ConsolePipeline())
                .addUrl("http://www.bttiantangs.com/").thread(5);
        spider.run();
    }

    public void stop() {
        spider.stop();
    }
}
