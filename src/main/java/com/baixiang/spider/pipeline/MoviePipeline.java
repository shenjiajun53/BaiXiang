package com.baixiang.spider.pipeline;

import com.baixiang.model.jpa.Actor;
import com.baixiang.model.jpa.Image;
import com.baixiang.model.jpa.Movie;
import com.baixiang.model.jpa.MovieTag;
import com.baixiang.model.common.SpiderMovieBean;
import com.baixiang.service.ActorService;
import com.baixiang.service.ImageService;
import com.baixiang.service.MovieService;
import com.baixiang.service.TagService;
import com.baixiang.utils.FileUtil;
import org.apache.http.util.TextUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

import java.util.List;

import static com.baixiang.config.PropertiesConfig.POSTER_PATH;

/**
 * Created by shenjj on 2017/6/21.
 */

@Service
//@ComponentScan()
public class MoviePipeline implements Pipeline {
    private static final Logger logger = LoggerFactory.getLogger(MoviePipeline.class);
    public final static String SPIDER_MOVIE_BEAN = "spider_movie_bean";
//    public final static String MOVIE_TITLE = "movie_title";
//    public final static String MOVIE_INFO = "movie_info";
//    public final static String MOVIE_POSTER = "movie_poster";
//    public final static String MOVIE_TAGS = "movie_tags";
//    public final static String MOVIE_ACTORS = "movie_actors";

    @Autowired
    private MovieService movieService;

    @Autowired
    private ActorService actorService;

    @Autowired
    private TagService tagService;

    @Autowired
    private ImageService imageService;

    @Override
    public void process(ResultItems resultItems, Task task) {
//        logger.info("result=" + resultItems.getRequest().getUrl());
        if (!resultItems.getRequest().getUrl().contains("www.bttiantangs.com/movie")) {
            return;
        }
        SpiderMovieBean spiderMovieBean = resultItems.get(SPIDER_MOVIE_BEAN);
//        logger.info(spiderMovieBean.toString());

        if (null != spiderMovieBean.getMovieName()) {
            List<Movie> movieList = movieService.getByName(spiderMovieBean.getMovieName());
            //TODO movieName 不唯一bug
            if (movieList.size() > 0) {
                Movie movie = movieList.get(0);
                setMovie(movie, spiderMovieBean);
                saveMovie(movie);
            } else {
                Movie movie = new Movie();
                setMovie(movie, spiderMovieBean);
                saveMovie(movie);
            }
        }
    }

    private void setMovie(Movie movie,
                          SpiderMovieBean spiderMovieBean) {
        movie.setMovieName(spiderMovieBean.getMovieName());
        movie.setMovieInfo(spiderMovieBean.getMovieInfo());
        movie.setDoubanId(spiderMovieBean.getDoubanId());
        movie.setDoubanUrl(spiderMovieBean.getDoubanUrl());
        movie.setImdbUrl(spiderMovieBean.getImdbUrl());
        for (String tagName : spiderMovieBean.getTagList()) {
            MovieTag movieTag = tagService.getMovieTagUnique(tagName);
            movie.addTag(movieTag);
        }
        for (String actorName : spiderMovieBean.getActorList()) {
            Actor actor = actorService.getActorUnique(actorName);
            movie.addActor(actor);
        }
        if (TextUtils.isEmpty(movie.getPosterUrl())) {
            setPoster(movie, spiderMovieBean.getPoster(), spiderMovieBean.getMovieName());
        }
    }

    private void saveMovie(Movie movie) {
        try {
            movieService.save(movie);
        } catch (ObjectOptimisticLockingFailureException e) {
            logger.error("ObjectOptimisticLockingFailureException save movie");
        }
    }

    private void setPoster(Movie movie, String moviePosterUrl, String movieTitle) {
        if (!TextUtils.isEmpty(moviePosterUrl)) {
            String fileName = System.currentTimeMillis() + "-" + movieTitle + ".jpg";
//            logger.info(fileName);
            String filePath = FileUtil.getFilePath(POSTER_PATH, fileName);
            FileUtil.downLoadFile(filePath, moviePosterUrl);
            Image image = new Image();
            image.setUrl(POSTER_PATH + fileName);
            image.setImageName(fileName);
            image.setType(Image.TYPE_POSTER);
            image = imageService.save(image);
            movie.setPosterUrl(image.getUrl());
            movie.setPosterId(image.getId());
        }
    }
}
