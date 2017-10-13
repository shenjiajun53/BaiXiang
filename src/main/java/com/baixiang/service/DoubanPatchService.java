package com.baixiang.service;

import com.alibaba.fastjson.JSON;
import com.baixiang.model.mongo.DoubanMovieBean;
import com.baixiang.model.jpa.Movie;
import com.baixiang.utils.logger.LogUtil;
import com.baixiang.utils.Urls;
import okhttp3.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class DoubanPatchService {
    private static final Logger logger = LoggerFactory.getLogger(DoubanPatchService.class);
    @Autowired
    private MovieService movieService;
    @Autowired
    private DoubanMovieService doubanMovieService;

    private int currentNum = 0;
    private Page<Movie> moviePage;
    private boolean isRunning = false;
    private OkHttpClient okHttpClient = new OkHttpClient();

    public void startDoubanPatch() {
        isRunning = true;
        Pageable pageable = new PageRequest(currentNum, 40);
        moviePage = movieService.getByPage(pageable);
        getDoubanMovieInfo(moviePage.getContent().get(currentNum));
    }

    private void getDoubanMovieInfo(Movie movie) {
        if (!isRunning) {
            return;
        }
        String url = String.format(Urls.DOUBAN_GET_MOVIE_INFO, movie.getDoubanId());
//        logger.info("url=" + url);
        LogUtil.info(DoubanPatchService.class, "url=" + url);
        Request request = new Request.Builder().tag("get_movie_info").url(url).build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String responseBody = response.body().string();
                DoubanMovieBean doubanMovieBean = JSON.parseObject(responseBody, DoubanMovieBean.class);
                if (doubanMovieBean.getId() != null) {
                    doubanMovieService.save(doubanMovieBean);
                }
                LogUtil.info(doubanMovieBean.toString());
                currentNum++;
                getDoubanMovieInfo(moviePage.getContent().get(currentNum));
            }
        });
    }

    public void stop() {
        isRunning = false;
        stopRequest();
    }

    public void stopRequest() {
        for (Call call : okHttpClient.dispatcher().queuedCalls()) {
            if ("get_movie_info".equals(call.request().tag())) {
                call.cancel();
            }
        }
        for (Call call : okHttpClient.dispatcher().runningCalls()) {
            if ("get_movie_info".equals(call.request().tag())) {
                call.cancel();
            }
        }
    }

    public boolean isRunning() {
        return isRunning;
    }

    public void setRunning(boolean running) {
        isRunning = running;
    }
}
