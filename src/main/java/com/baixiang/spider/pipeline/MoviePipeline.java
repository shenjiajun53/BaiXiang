package com.baixiang.spider.pipeline;

import com.baixiang.model.Movie;
import com.baixiang.repository.MovieRepository;
import com.baixiang.service.MovieService;
import com.baixiang.utils.FileUtil;
import okhttp3.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.PageModelPipeline;
import us.codecraft.webmagic.pipeline.Pipeline;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import static com.baixiang.utils.FileUtil.POSTER_PATH;
import static com.baixiang.utils.FileUtil.STATIC_PATH;

/**
 * Created by shenjj on 2017/6/21.
 */

@Component
public class MoviePipeline implements Pipeline {
    private static final Logger logger = LoggerFactory.getLogger(MoviePipeline.class);
    public final static String MOVIE_TITLE = "movie_title";
    public final static String MOVIE_INFO = "movie_info";
    public final static String MOVIE_POSTER = "movie_poster";

    @Autowired
    private MovieService movieService;

    @Override
    public void process(ResultItems resultItems, Task task) {
        String movieTitle = resultItems.get(MOVIE_TITLE);
        String movieInfo = resultItems.get(MOVIE_INFO);
        String moviePosterUrl = resultItems.get(MOVIE_POSTER);
        if (null != movieTitle) {
            Movie movie = new Movie();
            if (movieTitle.contains(":")) {
                movieTitle = movieTitle.replace(":", "：");
            }
            movie.setMovieName(movieTitle);
            movie.setMovieInfo(movieInfo);
            String fileName = System.currentTimeMillis() + "-" + movieTitle + ".jpg";
            logger.info(fileName);
            String filePath = getFilePath(POSTER_PATH, fileName);
            downLoadFile(filePath, moviePosterUrl);
            movie.setPoster(POSTER_PATH + fileName);
            movieService.saveOrUpdate(movie);
        }
    }

    public String getFilePath(String dirPath, String fileName) {
        String staticPath = System.getProperty("user.dir") + STATIC_PATH;
        FileUtil.createOrExistsDir(staticPath + dirPath);
        String filePath = staticPath + dirPath + fileName;
        logger.info(filePath);
        return filePath;
    }

    public void downLoadFile(String filePath, String url) {
        File file = new File(filePath);
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder().url(url)
                .build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                InputStream inputStream = response.body().byteStream();
                try {
                    FileOutputStream fout = new FileOutputStream(file);
                    int l = -1;
                    byte[] tmp = new byte[1024];
                    while ((l = inputStream.read(tmp)) != -1) {
                        fout.write(tmp, 0, l);
                    }
                    fout.flush();
                    fout.close();
                } finally {
                    inputStream.close();
                }
            }
        });
    }
}
