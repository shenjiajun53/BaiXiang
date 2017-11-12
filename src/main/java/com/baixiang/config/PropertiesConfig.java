package com.baixiang.config;

import com.baixiang.model.common.TomcatInitBean;
import com.baixiang.utils.logger.LogUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Configuration
//@Component
public class PropertiesConfig {

    @Autowired
    private Environment environment;

    @Value("${file.default-path}")
    private String rootPath;

    public static String ROOT_PATH = "";
    public static final String PROJECT_PATH = System.getProperty("user.dir");
    public static final String STATIC_PATH = PROJECT_PATH + "/src/main/webapp";
    public static final String POSTER_PATH = "/files/movie/posters/";
    public static final String SCREEN_SHOT_PATH = "/files/movie/screenShots/";
    public static final String TORRENT_PATH = "/files/movie/torrents/";

    @PostConstruct
    public void init() {
        LogUtil.info("init PropertiesConfig");
        ROOT_PATH = rootPath;
        LogUtil.info("ROOT_PATH=" + ROOT_PATH);
    }

    @PreDestroy
    public void destroy() {
        LogUtil.info("destroy PropertiesConfig");
    }


    public String getRootPath() {
//        LogUtil.info("rootPath=" + rootPath);
        return rootPath;
    }

    @Bean
    @Profile("debug")
    TomcatInitBean getDevTomcatInitBean() {
        return new TomcatInitBean(5006, "/");
    }

    @Bean
    @Profile("release")
    TomcatInitBean getProdTomcatInitBean() {
        return new TomcatInitBean(8080, "/baixiang");
    }
}
