package com.baixiang.controller;

import com.baixiang.model.Response;
import com.baixiang.model.SpiderStatusBean;
import com.baixiang.spider.Processor.BtTianTangProcessor;
import com.baixiang.spider.Processor.TaohuaProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import static com.baixiang.utils.Urls.*;

/**
 * Created by Administrator on 2017/6/19.
 */

@RestController
@ComponentScan(value = "com.baixiang.spider")
public class SpiderController {

    @Autowired
    BtTianTangProcessor btTianTangProcessor;

    @Autowired
    TaohuaProcessor taohuaProcessor;

    @RequestMapping(value = API_START_SPIDER_BT, method = RequestMethod.POST)
    public void startBt() {
        btTianTangProcessor.start();
    }

    @RequestMapping(value = API_STOP_SPIDER_BT, method = RequestMethod.POST)
    public void stopBt() {
        btTianTangProcessor.stop();
    }

    @RequestMapping(value = API_START_SPIDER_HUA, method = RequestMethod.POST)
    public void startTaohua() {
        taohuaProcessor.start();
    }

    @RequestMapping(value = API_STOP_SPIDER_HUA, method = RequestMethod.POST)
    public void stopTaohua() {
        taohuaProcessor.stop();
    }

    @RequestMapping(value = API_GET_SPIDER_STATUS, method = RequestMethod.POST)
    public Response<SpiderStatusBean> getSpiderStatus() {
        SpiderStatusBean spiderStatusBean = new SpiderStatusBean();
        spiderStatusBean.setBtRunning(btTianTangProcessor.isRunning());
        Response<SpiderStatusBean> response = new Response<>(spiderStatusBean, null);
        return response;
    }
}