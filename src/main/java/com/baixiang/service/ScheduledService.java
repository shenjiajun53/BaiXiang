package com.baixiang.service;

import com.baixiang.utils.logger.LogUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class ScheduledService {
    @Autowired
    DoubanPatchService doubanPatchService;

    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("YYYY-MM-dd-HH-mm-ss");

//    @Scheduled(fixedRate = 1000)
//    public void startLogging() {
//        LogUtil.info(simpleDateFormat.format(new Date()));
//    }

    //一小时执行一次
    @Scheduled(fixedRate = 360000)
    public void startDoubanPatch() {
        doubanPatchService.startDoubanPatch();
    }
}
