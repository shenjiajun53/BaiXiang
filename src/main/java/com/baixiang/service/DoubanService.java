package com.baixiang.service;

import com.baixiang.model.Movie;
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
public class DoubanService {
    private static final Logger logger = LoggerFactory.getLogger(DoubanService.class);
    @Autowired
    private MovieService movieService;
    private int currentNum = 0;

    public void startDoubanPatch() {
        int i = 0;
        Pageable pageable = new PageRequest(currentNum, 40);
        Page<Movie> moviePage = movieService.getByPage(pageable);
        for (Movie movie : moviePage) {
            if (i < 5) {
                i++;
                getDoubanMovieInfo(movie.getDoubanId());
            }
        }
    }

    private void getDoubanMovieInfo(long doubanId) {
        OkHttpClient okHttpClient = new OkHttpClient();
        String url = String.format(Urls.DOUBAN_GET_MOVIE_INFO, doubanId);
//        logger.info("url=" + url);
        LogUtil.info(DoubanService.class, "url=" + url);
        Request request = new Request.Builder().tag("get_movie_info").url(url).build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String responseBody = response.body().string();
                LogUtil.json(responseBody);
            }
        });
    }
}
