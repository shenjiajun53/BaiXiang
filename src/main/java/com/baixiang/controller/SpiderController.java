package com.baixiang.controller;

import com.baixiang.spider.BtTianTangProcessor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Administrator on 2017/6/19.
 */

@RestController
public class SpiderController {

    @RequestMapping(value = "/api/start_spider_bt", method = RequestMethod.POST)
    public void startBt(){
        BtTianTangProcessor.start();
    }

    @RequestMapping(value = "/api/stop_spider_bt", method = RequestMethod.POST)
    public void stopBt(){
        BtTianTangProcessor.stop();
    }
}
