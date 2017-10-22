package com.baixiang.spider.pipeline;

import com.baixiang.config.PropertiesConfig;
import com.baixiang.model.jpa.Movie;
import com.baixiang.model.jpa.MovieTorrent;
import com.baixiang.model.common.SpiderTorrentBean;
import com.baixiang.service.MovieService;
import com.baixiang.service.TorrentService;
import com.baixiang.utils.FileUtil;
import org.apache.http.util.TextUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

import static com.baixiang.config.PropertiesConfig.TORRENT_PATH;

/**
 * Created by shenjj on 2017/6/29.
 */

@Component
public class TorrentPipeline implements Pipeline {
    private static final Logger logger = LoggerFactory.getLogger(TorrentPipeline.class);
    public final static String SPIDER_TORRENT_BEAN = "spider_torrent_bean";
//    public final static String TORRENT_NAME = "torrent_title";
//    public final static String MAGNET_URL = "magnet_url";
//    public final static String TORRENT_URL = "torrent_url";
//    public final static String TORRENT_MOVIE_TITLE = "torrent_movie_title";

    @Autowired
    private PropertiesConfig propertiesConfig;

    @Autowired
    private MovieService movieService;

    @Autowired
    TorrentService torrentService;

    @Override
    public void process(ResultItems resultItems, Task task) {
        if (!resultItems.getRequest().getUrl().contains("www.bttiantangs.com/download")) {
            return;
        }
        SpiderTorrentBean spiderTorrentBean = resultItems.get(SPIDER_TORRENT_BEAN);
        String movieTitle = spiderTorrentBean.getMovieName();
        String torrentName = spiderTorrentBean.getTorrentName();
        String magnetUrl = spiderTorrentBean.getMagnetUrl();
        String torrentUrl = spiderTorrentBean.getTorrentUrl();
//        logger.info(spiderTorrentBean.toString());

        if (!TextUtils.isEmpty(torrentName)) {
            if (torrentService.getIncludeName(torrentName).size() > 0) {
                MovieTorrent movieTorrent = torrentService.getIncludeName(torrentName).get(0);
                if (TextUtils.isEmpty(movieTorrent.getFilePath())) {
                    setTorrentFile(torrentUrl, torrentName, movieTorrent);
                }
                movieTorrent.setMagnetUrl(magnetUrl);
                torrentService.update(movieTorrent);
            } else if (!TextUtils.isEmpty(movieTitle) &&
                    movieService.getIncludeName(movieTitle).size() > 0) {
                MovieTorrent movieTorrent = new MovieTorrent();
                movieTorrent.setTorrentName(torrentName);
                movieTorrent.setMagnetUrl(magnetUrl);
                setTorrentFile(torrentUrl, torrentName, movieTorrent);
                Movie movie = movieService.getIncludeName(movieTitle).get(0);
                movie.addTorrent(movieTorrent);
                saveMovie(movie);
            }
        }
    }

    private void saveMovie(Movie movie) {
        try {
            movieService.save(movie);
        } catch (InvalidDataAccessApiUsageException e) {
            logger.error("InvalidDataAccessApiUsageException save movie");
        } catch (ObjectOptimisticLockingFailureException e) {
            logger.error("ObjectOptimisticLockingFailureException save movie");
        }
    }

    public void setTorrentFile(String torrentUrl, String torrentName, MovieTorrent movieTorrent) {
        if (!TextUtils.isEmpty(torrentUrl)) {
            String fileName = System.currentTimeMillis() + "-" + torrentName + ".torrent";
            String filePath = FileUtil.getFilePath(propertiesConfig.getRootPath(), TORRENT_PATH, fileName);
            FileUtil.downLoadFile(filePath, torrentUrl);
            movieTorrent.setFilePath(TORRENT_PATH + fileName);
        }
    }
}
