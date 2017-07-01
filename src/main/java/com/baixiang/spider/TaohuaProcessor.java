package com.baixiang.spider;

import com.baixiang.utils.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import static com.baixiang.spider.FilmPipeline.FILM_INFO;
import static com.baixiang.spider.FilmPipeline.FILM_TITLE;

/**
 * Created by Administrator on 2017/6/19.
 */

@Component
public class TaohuaProcessor implements PageProcessor {
    private Site site = Site.me().setRetryTimes(3).setSleepTime(1000).setTimeOut(10000);
    private static Spider spider;
    private static final String STATIC_PATH = "/spider_file";
    private static final String FILE_PATH = "/taohua/";

    @Autowired
    private FilmPipeline filmPipeline;

    public void start() {
        spider = Spider.create(this)
                .addPipeline(filmPipeline)
                .addUrl("http://thzav.com/forum.php").thread(5);
        spider.run();
    }

    public void stop() {
        spider.stop();
    }

    public void main(String[] args) {
        spider.run();
    }

    @Override
    public Site getSite() {
        return site;
    }

    @Override
    public void process(Page page) {
//        page.addTargetRequests(page.getHtml().links().regex("http://thzav.com/forum-\\w+.*").all());
        page.addTargetRequests(page.getHtml().links().regex("http://thzav.com/forum-\\w+-1.*").all());
        String s = "http://thzav.com/thread-1085966-1-1.html";
        page.addTargetRequests(page.getHtml().links().regex("http://thzav.com/thread-\\w+-1-\\w+.*").all());
        page.putField(FILM_TITLE, page.getHtml().xpath("//span[@id='thread_subject']").toString());
        page.putField(FILM_INFO, page.getHtml().xpath("//td[@class='t_f']").toString());
        if (page.getResultItems().toString().contains("本田岬")) {
            writeFile(page.getUrl().toString() + " 本田岬 \n");
        }
        if (page.getResultItems().toString().contains("今永")) {
            writeFile(page.getUrl().toString() + " 今永 \n");
        }
        if (page.getResultItems().toString().contains("三上悠亜")) {
            writeFile(page.getUrl().toString() + " 三上悠亜 \n");
        }
        if (page.getResultItems().toString().contains("神咲詩織")) {
            writeFile(page.getUrl().toString() + " 神咲詩織 \n");
        }
        if (page.getResultItems().toString().contains("水野朝陽")) {
            writeFile(page.getUrl().toString() + " 水野朝陽 \n");
        }
        if (page.getResultItems().toString().contains("工藤美纱")) {
            writeFile(page.getUrl().toString() + " 工藤美纱 \n");
        }
    }

    public static void writeFile(String str) {
        String staticPath = System.getProperty("user.dir") + STATIC_PATH;
        FileUtil.createOrExistsDir(staticPath + FILE_PATH);

        String fileName = "链接.txt";
        File file = new File(staticPath + FILE_PATH + fileName);
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(file, true));
            bw.write(str + "\r\n\r\n");
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
