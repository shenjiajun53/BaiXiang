package com.baixiang.spider.processor;

import com.baixiang.config.PropertiesConfig;
import com.baixiang.model.common.SpiderMovieBean;
import com.baixiang.model.common.SpiderTorrentBean;
import com.baixiang.spider.pipeline.MoviePipeline;
import com.baixiang.spider.pipeline.TorrentPipeline;
import com.baixiang.utils.FileUtil;
import com.baixiang.utils.RegexUtil;
import com.baixiang.utils.logger.LogUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.scheduler.FileCacheQueueScheduler;
import us.codecraft.webmagic.selector.Selectable;

import java.util.ArrayList;
import java.util.List;

import static com.baixiang.spider.pipeline.MoviePipeline.*;
import static com.baixiang.spider.pipeline.TorrentPipeline.*;

/**
 * Created by shenjj on 2017/6/19.
 */

@Service
public class BtTianTangProcessor implements PageProcessor {
    private static final Logger logger = LoggerFactory.getLogger(BtTianTangProcessor.class);
    public final static String MOVIE_TITLE = "movie_title";

    @Autowired
    private PropertiesConfig propertiesConfig;

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
//                .setScheduler(new PriorityScheduler())
                .setScheduler(new FileCacheQueueScheduler(propertiesConfig.getRootPath() + "/webmagic"))
                .addPipeline(moviePipeline)
//                .addPipeline(new ConsolePipeline())
                .addPipeline(torrentPipeline)
//                .addUrl("http://www.bttiantangs.com/movie/41714.html")
                .addUrl("http://www.bttiantangs.com/")
                .thread(3);
        spider.run();
    }

    public void stop() {
        isRunning = false;
        if (null != spider) {
            spider.stop();
        }
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
//        logger.info("visit=" + page.getUrl().toString());
        if (page.getUrl().toString().contains("www.bttiantangs.com/movie")) {
            parseMovie(page);
        }
        if (page.getUrl().toString().contains("www.bttiantangs.com/download")) {
            parseTorrent(page);
        }
//        page.addTargetRequests(page.getHtml().links().regex("(http://www\\.bttiantangs\\.com/movie/[\\w\\-]+/[\\w\\-]+)").all());
//        page.addTargetRequests(page.getHtml().links().regex("http://www.bttiantangs.com/movie/\\d+.*").all());
        List<String> links = page.getHtml().links().regex("http://www.bttiantangs.com/movie/\\d+.html").all();
        for (String link : links) {
            Request request = new Request(link).setPriority(2);
            page.addTargetRequest(request);
        }
    }

    private void parseMovie(Page page) {
        String titleStr = page.getHtml().xpath("//div[@class='article_container']/h1/text()").toString();
        //过滤标题中的年份,例：(2017)
        titleStr = titleStr.replaceAll("\\(\\d\\d\\d\\d\\)", "");
        if (titleStr == null) {
            //skip this page
            page.setSkip(true);
            logger.info("skip movie page title=null " + page.getUrl());
        }
        logger.info("Parse Movie " + titleStr);
        SpiderMovieBean spiderMovieBean = new SpiderMovieBean();
        spiderMovieBean.setMovieName(titleStr);
        spiderMovieBean.setMovieInfo(page.getHtml().xpath("//p[@class='minfos']").toString());
        spiderMovieBean.setPoster(page.getHtml().xpath("//p[@class='tpic-cont-s']").css("img", "src").toString());

        List<Selectable> tagNodes = page.getHtml().xpath("//span[@class='info_category']/a/text()").nodes();
        ArrayList<String> tagList = new ArrayList<>();
        for (Selectable node : tagNodes) {
//                logger.info(tagNodes.size() + ":" + tagNodes.get(i).get());
            tagList.add(node.get());
        }
        spiderMovieBean.setTagList(tagList);

        Selectable contentNodes = page.getHtml().xpath("//div[@id='post_content']/p[2]");
        if (null != contentNodes && null != contentNodes.get()) {
            String[] contentList = contentNodes.get().split("<br>");
            for (String str : contentList) {
//            logger.info("str=" + str);
                if (str.contains("movie.douban.com")) {
                    String doubanUrl = RegexUtil.findFirst(str, "[a-zA-z]+://[^\\s]*");
                    doubanUrl = doubanUrl.replaceAll("/\"", "");
                    String doubanId = doubanUrl.replaceAll("http://movie.douban.com/subject/", "");
                    spiderMovieBean.setDoubanId(doubanId);
                    spiderMovieBean.setDoubanUrl(doubanUrl);
                }
                if (str.contains("www.imdb.com")) {
                    //匹配出url
                    String imdbUrl = RegexUtil.findFirst(str, "[a-zA-z]+://[^\\s]*");
                    spiderMovieBean.setImdbUrl(imdbUrl);
                }
            }
            List<Selectable> linkNodes = contentNodes.xpath("//a").nodes();
            ArrayList<String> actorList = new ArrayList<>();
            for (Selectable node : linkNodes) {
                if (node.css("a", "href").get().contains("/zhuyan/")) {
                    actorList.add(node.xpath("/a/text()").get());
                }
            }
            spiderMovieBean.setActorList(actorList);
        } else {
            page.setSkip(true);
            logger.info("skip movie page content=null " + page.getUrl());
        }

        page.putField(SPIDER_MOVIE_BEAN, spiderMovieBean);

        List<String> links = page.getHtml().links().regex("http://www.bttiantangs.com/download/\\d+.html").all();
        for (String link : links) {
            Request request = new Request(link).setPriority(3).putExtra(MOVIE_TITLE, titleStr);
            page.addTargetRequest(request);
        }
    }

    private void parseTorrent(Page page) {
        SpiderTorrentBean spiderTorrentBean = new SpiderTorrentBean();
        if (null != page.getRequest().getExtra(MOVIE_TITLE)) {
            spiderTorrentBean.setMovieName(page.getRequest().getExtra(MOVIE_TITLE).toString());
        } else {
            spiderTorrentBean.setMovieName(page.getHtml().xpath("//div[@class='article_container']/h1/a/text()").toString());
        }
        spiderTorrentBean.setMagnetUrl(page.getHtml().xpath("//span[@id='link_text_span']/text()").toString());
        List<Selectable> contentNodes = page.getHtml().xpath("//div[@id='post_content']/p").nodes();
        for (Selectable node : contentNodes) {
            if (node.toString().contains("名　　称")) {
                String rawName = node.toString();
                String[] filter = rawName.split("<br>");
                String torrentName = filter[0].replaceAll("◎名　　称　", "");
                torrentName = torrentName.replaceAll("<p>", "");
                spiderTorrentBean.setTorrentName(torrentName);
                logger.info("Parse Torrent " + torrentName);
                break;
            }
        }
        List<Selectable> downLoadNodes = page.getHtml().xpath("//a[@id='link_org_a']").nodes();
        for (Selectable node : downLoadNodes) {
            if (node.toString().contains("BT种子")) {
                String torrentUrl = node.css("a", "href").toString();
                if (torrentUrl.startsWith("http")) {
//                    logger.info("url=" + torrentUrl);
                    spiderTorrentBean.setTorrentUrl(torrentUrl);
                }
            }
        }
        page.putField(SPIDER_TORRENT_BEAN, spiderTorrentBean);

        List<String> links = page.getHtml().links().regex("http://www.bttiantangs.com/download/\\d+.html").all();
        for (String link : links) {
            Request request = new Request(link).setPriority(4).putExtra(MOVIE_TITLE, page.getResultItems().get(MOVIE_TITLE));
            page.addTargetRequest(request);
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
