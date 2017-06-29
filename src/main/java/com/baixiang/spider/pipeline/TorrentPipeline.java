package com.baixiang.spider.pipeline;

import com.baixiang.model.Movie;
import com.baixiang.model.MovieTorrent;
import com.baixiang.repository.MovieRepository;
import com.baixiang.repository.TorrentRepository;
import com.baixiang.utils.FileUtil;
import okhttp3.*;
import org.apache.http.util.TextUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import static com.baixiang.spider.pipeline.MoviePipeline.MOVIE_TITLE;
import static com.baixiang.utils.FileUtil.POSTER_PATH;
import static com.baixiang.utils.FileUtil.STATIC_PATH;

/**
 * Created by shenjj on 2017/6/29.
 */

@Component
public class TorrentPipeline implements Pipeline {
    private static final Logger logger = LoggerFactory.getLogger(MoviePipeline.class);
    public final static String TORRENT_NAME = "movie_title";
    public final static String MAGNET_URL = "movie_info";
    public final static String TORRENT_MOVIE_TITLE = "torrent_movie_title";

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private TorrentRepository torrentRepository;

    @Override
    public void process(ResultItems resultItems, Task task) {
        if (!resultItems.getRequest().getUrl().contains("www.bttiantangs.com/download")) {
            return;
        }

        String movieTitle = resultItems.get(TORRENT_MOVIE_TITLE);
        String torrentName = resultItems.get(TORRENT_NAME);
        String magnetUrl = resultItems.get(MAGNET_URL);

        if (!TextUtils.isEmpty(torrentName)) {
            if (torrentRepository.getIncludeName(torrentName).size() > 0) {
                MovieTorrent oldTorrent = torrentRepository.getIncludeName(torrentName).get(0);
                oldTorrent.setMagnetUrl(magnetUrl);
                torrentRepository.update(oldTorrent);
            } else if (!TextUtils.isEmpty(movieTitle)) {
                if (movieRepository.getIncludeName(movieTitle).size() > 0) {
                    MovieTorrent movieTorrent = new MovieTorrent();
                    movieTorrent.setTorrentName(torrentName);
                    movieTorrent.setMagnetUrl(magnetUrl);

                    Movie movie = movieRepository.getIncludeName(movieTitle).get(0);
                    movie.addTorrent(movieTorrent);
                    movieRepository.update(movie);
                }
            }
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
