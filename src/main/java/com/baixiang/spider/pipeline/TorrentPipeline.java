package com.baixiang.spider.pipeline;

import com.baixiang.model.Movie;
import com.baixiang.model.MovieTorrent;
import com.baixiang.repository.TorrentRepository;
import com.baixiang.service.MovieService;
import com.baixiang.utils.FileUtil;
import org.apache.http.util.TextUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

import static com.baixiang.utils.FileUtil.TORRENT_PATH;

/**
 * Created by shenjj on 2017/6/29.
 */

@Component
public class TorrentPipeline implements Pipeline {
    private static final Logger logger = LoggerFactory.getLogger(MoviePipeline.class);
    public final static String TORRENT_NAME = "torrent_title";
    public final static String MAGNET_URL = "magnet_url";
    public final static String TORRENT_URL = "torrent_url";
    public final static String TORRENT_MOVIE_TITLE = "torrent_movie_title";


    @Autowired
    private MovieService movieService;

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
        String torrentUrl = resultItems.get(TORRENT_URL);

        if (!TextUtils.isEmpty(torrentName)) {
            if (torrentRepository.getIncludeName(torrentName).size() > 0) {
                MovieTorrent oldTorrent = torrentRepository.getIncludeName(torrentName).get(0);
                if (TextUtils.isEmpty(oldTorrent.getFilePath())) {
                    setTorrentFile(torrentUrl, torrentName, oldTorrent);
                }
                oldTorrent.setMagnetUrl(magnetUrl);
                torrentRepository.update(oldTorrent);
            } else if (!TextUtils.isEmpty(movieTitle)) {
                if (movieService.getIncludeName(movieTitle).size() > 0) {
                    MovieTorrent movieTorrent = new MovieTorrent();
                    movieTorrent.setTorrentName(torrentName);
                    movieTorrent.setMagnetUrl(magnetUrl);
                    setTorrentFile(torrentUrl, torrentName, movieTorrent);
                    Movie movie = movieService.getIncludeName(movieTitle).get(0);
                    movie.addTorrent(movieTorrent);
                    movieService.save(movie);
                }
            }
        }
    }

    public void setTorrentFile(String torrentUrl, String torrentName, MovieTorrent movieTorrent) {
        if (!TextUtils.isEmpty(torrentUrl)) {
            String fileName = System.currentTimeMillis() + "-" + torrentName + ".torrent";
            String filePath = FileUtil.getFilePath(TORRENT_PATH, fileName);
            FileUtil.downLoadFile(filePath, torrentUrl);
            movieTorrent.setFilePath(TORRENT_PATH + fileName);
        }
    }
}
