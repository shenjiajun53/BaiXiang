package com.baixiang.spider.BtTianTang;

import com.baixiang.spider.pipeline.MoviePipeline;
import com.baixiang.spider.pipeline.TorrentPipeline;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.ConsolePipeline;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Selectable;

import java.util.List;

import static com.baixiang.spider.pipeline.MoviePipeline.MOVIE_INFO;
import static com.baixiang.spider.pipeline.MoviePipeline.MOVIE_POSTER;
import static com.baixiang.spider.pipeline.MoviePipeline.MOVIE_TITLE;
import static com.baixiang.spider.pipeline.TorrentPipeline.*;

/**
 * Created by shenjj on 2017/6/19.
 */

@Component
@ComponentScan(value = "com.baixiang.spider")
public class BtTianTangProcessor implements PageProcessor {
    private static final Logger logger = LoggerFactory.getLogger(BtTianTangProcessor.class);
    @Autowired
    private MoviePipeline moviePipeline;

    @Autowired
    private TorrentPipeline torrentPipeline;

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
        page.addTargetRequests(page.getHtml().links().regex("http://www.bttiantangs.com/download/\\d+.*").all());

//        logger.info("visit=" + page.getUrl().toString());
        if (page.getUrl().toString().contains("www.bttiantangs.com/movie")) {
            page.putField(MOVIE_TITLE, page.getHtml().xpath("//div[@class='article_container']/h1/text()").toString());
            page.putField(MOVIE_INFO, page.getHtml().xpath("//p[@class='minfos']").toString());
            page.putField(MOVIE_POSTER, page.getHtml().xpath("//p[@class='tpic-cont-s']").css("img", "src").toString());

            if (page.getResultItems().get(MOVIE_TITLE) == null) {
                //skip this page
                page.setSkip(true);
            }
        }
        if (page.getUrl().toString().contains("www.bttiantangs.com/download")) {
            page.putField(TORRENT_MOVIE_TITLE, page.getHtml().xpath("//div[@class='article_container']/h1/a/text()").toString());
            page.putField(MAGNET_URL, page.getHtml().xpath("//span[@id='link_text_span']/text()").toString());
            List<Selectable> contentNodes = page.getHtml().xpath("//div[@id='post_content']/p").nodes();
            for (int i = 0; i < contentNodes.size(); i++) {
                if (contentNodes.get(i).toString().contains("名　　称")) {
                    try {
                        String rawName = contentNodes.get(i).toString();
                        String[] filter1 = rawName.split("　");
//                    logger.info("filter1=" + toString(filter1));
                        String[] filter2 = filter1[3].split("<");
//                    logger.info("filter2=" + toString(filter2));
                        String torrentName = filter2[0];
//                        logger.info("torrentName=" + torrentName);
                        page.putField(TORRENT_NAME, torrentName);
                    } catch (Exception e) {

                    }
                }
            }
            List<Selectable> downLoadNodes = page.getHtml().xpath("//a[@id='link_org_a']").nodes();
            for (int i = 0; i < downLoadNodes.size(); i++) {
                if (downLoadNodes.get(i).toString().contains("BT种子")) {
//                    logger.info("url=" + downLoadNodes.get(i).css("a", "href").toString());
                    String torrentUrl = downLoadNodes.get(i).css("a", "href").toString();
//                    logger.info("11url=" + torrentUrl);
                    if (torrentUrl.startsWith("https")) {
                        logger.info("url=" + torrentUrl);
                        page.putField(TORRENT_URL, torrentUrl);
                    }
                }
            }

            if (page.getResultItems().get(TORRENT_MOVIE_TITLE) == null) {
                //skip this page
                page.setSkip(true);
            }
        }
    }

    public String toString(String[] arrayStr) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < arrayStr.length; i++) {
            stringBuilder.append(arrayStr[i] + "|");
        }
        return stringBuilder.toString();
    }


    public void start() {
        spider = Spider.create(this)
                .addPipeline(moviePipeline)
//                .addPipeline(new ConsolePipeline())
                .addPipeline(torrentPipeline)
                .addUrl("http://www.bttiantangs.com/").thread(5);
        spider.run();
    }

    public void stop() {
        spider.stop();
    }
}
