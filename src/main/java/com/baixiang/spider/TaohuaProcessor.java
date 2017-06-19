package com.baixiang.spider;

import com.baixiang.utils.FileUtil;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by Administrator on 2017/6/19.
 */
public class TaohuaProcessor implements PageProcessor {
    private Site site = Site.me().setRetryTimes(3).setSleepTime(500).setTimeOut(10000);
    private static Spider spider = Spider.create(new TaohuaProcessor()).addUrl("http://thzav.com/forum.php").thread(5);
    private static final String STATIC_PATH = "/spider_file";
    private static final String FILE_PATH = "/taohua/";

    @Override
    public Site getSite() {
        return site;
    }

    @Override
    public void process(Page page) {
//        page.addTargetRequests(page.getHtml().links().regex("(http://thzav\\.com/[\\w\\-]+/[\\w\\-]+)").all());
        page.addTargetRequests(page.getHtml().links().regex("http://thzav.com/\\d+.*").all());
        page.putField("movie_content", page.getHtml().xpath("//td[@class='t_f']/text()").toString());
//        if (page.getResultItems().get("movie_content") == null) {
//            //skip this page
//            page.setSkip(true);
//            if (page.getResultItems().get("movie_content").toString().contains("本田岬")) {
//               writeFile(page.getResultItems().get("movie_content").toString());
//            }
//        }
    }

    public static void writeFile(String str)
    {
        String staticPath = System.getProperty("user.dir") + STATIC_PATH;
        FileUtil.createOrExistsDir(staticPath + FILE_PATH);

        String fileName = System.currentTimeMillis() + ".txt";
        File file = new File(staticPath + FILE_PATH + fileName);
        try {
            BufferedWriter bw=new BufferedWriter(new FileWriter(file,true));
            bw.write(str+"\r\n\r\n");
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void start() {
        spider.run();
    }

    public static void stop() {
        spider.stop();
    }

    public static void main(String[] args) {
        spider.run();
    }

}
