package com.baixiang.spider.pipeline;

import com.baixiang.model.Movie;
import com.baixiang.repository.MovieHibernateRepository;
import com.baixiang.utils.FileUtil;
import org.apache.http.util.TextUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

import java.util.HashSet;

import static com.baixiang.utils.FileUtil.POSTER_PATH;

/**
 * Created by shenjj on 2017/6/21.
 */

@Component
public class MoviePipeline implements Pipeline {
    private static final Logger logger = LoggerFactory.getLogger(MoviePipeline.class);
    public final static String MOVIE_TITLE = "movie_title";
    public final static String MOVIE_INFO = "movie_info";
    public final static String MOVIE_POSTER = "movie_poster";
    public final static String MOVIE_TAGS = "movie_tags";
    public final static String MOVIE_ACTORS = "movie_actors";

    @Autowired
    private MovieHibernateRepository movieRepository;

    @Override
    public void process(ResultItems resultItems, Task task) {
//        logger.info("result=" + resultItems.getRequest().getUrl());
        if (!resultItems.getRequest().getUrl().contains("www.bttiantangs.com/movie")) {
            return;
        }
        String movieTitle = resultItems.get(MOVIE_TITLE);
        String movieInfo = resultItems.get(MOVIE_INFO);
        String moviePosterUrl = resultItems.get(MOVIE_POSTER);
        HashSet<String> tagSet = resultItems.get(MOVIE_TAGS);
        if (null != movieTitle) {
            Movie movie = new Movie();
            if (movieTitle.contains(":")) {
                movieTitle = movieTitle.replace(":", "：");
            }
            movie.setMovieName(movieTitle);
            movie.setMovieInfo(movieInfo);
            movie.setMovieTagSet(tagSet);

            if (movieRepository.getIncludeName(movieTitle).size() > 0) {
                Movie existMovie = movieRepository.getIncludeName(movieTitle).get(0);
                movie.setId(existMovie.getId());
                if (TextUtils.isEmpty(existMovie.getPoster())) {
                    setPoster(movie, moviePosterUrl, movieTitle);
                }
                movieRepository.update(movie);
            } else {
                setPoster(movie, moviePosterUrl, movieTitle);
                movieRepository.save(movie);
            }
        }
    }

    public void setPoster(Movie movie, String moviePosterUrl, String movieTitle) {
        if (!TextUtils.isEmpty(moviePosterUrl)) {
            String fileName = System.currentTimeMillis() + "-" + movieTitle + ".jpg";
//            logger.info(fileName);
            String filePath = FileUtil.getFilePath(POSTER_PATH, fileName);
            FileUtil.downLoadFile(filePath, moviePosterUrl);
            movie.setPoster(POSTER_PATH + fileName);
        }
    }
}
