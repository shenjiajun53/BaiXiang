package com.baixiang.spider.Processor;

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

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static com.baixiang.spider.pipeline.MoviePipeline.*;
import static com.baixiang.spider.pipeline.TorrentPipeline.*;

/**
 * Created by shenjj on 2017/6/19.
 */

@Component
@ComponentScan()
public class BtTianTangProcessor implements PageProcessor {
    private static final Logger logger = LoggerFactory.getLogger(BtTianTangProcessor.class);
    @Autowired
    private MoviePipeline moviePipeline;

    @Autowired
    private TorrentPipeline torrentPipeline;

    private Site site = Site.me().setRetryTimes(5).setSleepTime(1500).setTimeOut(20000);
    private Spider spider;

    private boolean isRunning = false;

    public BtTianTangProcessor() {
    }


    public void start() {
        isRunning = true;
        spider = Spider.create(this)
                .addPipeline(moviePipeline)
//                .addPipeline(new ConsolePipeline())
                .addPipeline(torrentPipeline)
//                .addUrl("http://www.bttiantangs.com/list/dianying/index.html")
                .addUrl("http://www.bttiantangs.com/")
                .thread(3);
        spider.run();
    }

    public void stop() {
        isRunning = false;
        spider.stop();
    }

    public boolean isRunning() {
        return isRunning;
    }

    public void setRunning(boolean running) {
        isRunning = running;
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
            parseMovie(page);
        }
        if (page.getUrl().toString().contains("www.bttiantangs.com/download")) {
            parseTorrent(page);
        }
    }

    private void parseMovie(Page page) {
        page.putField(MOVIE_TITLE, page.getHtml().xpath("//div[@class='article_container']/h1/text()").toString());
        page.putField(MOVIE_INFO, page.getHtml().xpath("//p[@class='minfos']").toString());
        page.putField(MOVIE_POSTER, page.getHtml().xpath("//p[@class='tpic-cont-s']").css("img", "src").toString());
        List<Selectable> tagNodes = page.getHtml().xpath("//span[@class='info_category']/a/text()").nodes();
        ArrayList<String> tagList = new ArrayList<>();
        for (int i = 0; i < tagNodes.size(); i++) {
//                logger.info(tagNodes.size() + ":" + tagNodes.get(i).get());
            tagList.add(tagNodes.get(i).get());
        }
        page.putField(MOVIE_TAGS, tagList);
        List<Selectable> actorNodes = page.getHtml().xpath("//div[@id='post_content']/p[2]/a").nodes();
        ArrayList<String> actorList = new ArrayList<>();
        for (int i = 0; i < actorNodes.size(); i++) {
            if (actorNodes.get(i).css("a", "href").get().contains("/zhuyan/")) {
                actorList.add(actorNodes.get(i).xpath("/a/text()").get());
            }
        }
        page.putField(MOVIE_ACTORS, actorList);
        if (page.getResultItems().get(MOVIE_TITLE) == null) {
            //skip this page
            page.setSkip(true);
        }
    }

    private void parseTorrent(Page page) {
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

    public String toString(String[] arrayStr) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < arrayStr.length; i++) {
            stringBuilder.append(arrayStr[i] + "|");
        }
        return stringBuilder.toString();
    }
}