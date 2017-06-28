package com.baixiang.controller;

import com.baixiang.spider.BtTianTang.BtTianTangProcessor;
import com.baixiang.spider.TaohuaProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Administrator on 2017/6/19.
 */

@RestController
@ComponentScan(value = "com.baixiang.spider")
public class SpiderController {

    @Autowired
    BtTianTangProcessor btTianTangProcessor;

    @RequestMapping(value = "/api/start_spider_bt", method = RequestMethod.POST)
    public void startBt() {
        btTianTangProcessor.start();
    }

    @RequestMapping(value = "/api/stop_spider_bt", method = RequestMethod.POST)
    public void stopBt() {
        btTianTangProcessor.stop();
    }

    @RequestMapping(value = "/api/start_spider_taohua", method = RequestMethod.POST)
    public void startTaohua() {
        TaohuaProcessor.start();
    }

    @RequestMapping(value = "/api/stop_spider_taohua", method = RequestMethod.POST)
    public void stopTaohua() {
        TaohuaProcessor.stop();
    }
}
