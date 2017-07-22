package com.baixiang.spider.pipeline;

import com.baixiang.model.Actor;
import com.baixiang.model.Movie;
import com.baixiang.service.ActorService;
import com.baixiang.service.MovieService;
import com.baixiang.utils.FileUtil;
import org.apache.http.util.TextUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

import java.util.HashSet;
import java.util.Set;

import static com.baixiang.utils.FileUtil.POSTER_PATH;

/**
 * Created by shenjj on 2017/6/21.
 */

@Component
@ComponentScan()
public class MoviePipeline implements Pipeline {
    private static final Logger logger = LoggerFactory.getLogger(MoviePipeline.class);
    public final static String MOVIE_TITLE = "movie_title";
    public final static String MOVIE_INFO = "movie_info";
    public final static String MOVIE_POSTER = "movie_poster";
    public final static String MOVIE_TAGS = "movie_tags";
    public final static String MOVIE_ACTORS = "movie_actors";

    @Autowired
    private MovieService movieService;

    @Autowired
    private ActorService actorService;

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
        HashSet<String> actorSet = resultItems.get(MOVIE_ACTORS);
        if (null != movieTitle) {
            if (movieTitle.contains(":")) {
                movieTitle = movieTitle.replace(":", "：");
            }
            if (movieService.getIncludeName(movieTitle).size() > 0) {
                Movie movie = movieService.getIncludeName(movieTitle).get(0);
                setMovie(movie, movieTitle, movieInfo, tagSet, actorSet, moviePosterUrl);
                saveMovie(movie);
            } else {
                Movie movie = new Movie();
                setMovie(movie, movieTitle, movieInfo, tagSet, actorSet, moviePosterUrl);
                saveMovie(movie);
            }
        }
    }

    private void setMovie(Movie movie,
                          String movieTitle,
                          String movieInfo,
                          Set<String> tagSet,
                          HashSet<String> actorSet,
                          String moviePosterUrl) {
        movie.setMovieName(movieTitle);
        movie.setMovieInfo(movieInfo);
        movie.setMovieTagSet(tagSet);
        for (String actorName : actorSet) {
            Actor actor = actorService.getActorByName(actorName);
            if (actor == null) {
                actor = new Actor();
                actor.setActorName(actorName);
            }
            movie.addActor(actor);
        }
        if (TextUtils.isEmpty(movie.getPoster())) {
            setPoster(movie, moviePosterUrl, movieTitle);
        }
    }

    private void saveMovie(Movie movie) {
        try {
            movieService.save(movie);
        } catch (ObjectOptimisticLockingFailureException e) {
            logger.error("ObjectOptimisticLockingFailureException save movie");
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
