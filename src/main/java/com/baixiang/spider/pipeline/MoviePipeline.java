package com.baixiang.spider.pipeline;

import com.baixiang.model.Movie;
import com.baixiang.repository.MovieRepository;
import com.baixiang.service.MovieService;
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

/**
 * Created by shenjj on 2017/6/21.
 */

@Component
public class MoviePipeline implements Pipeline {
    private static final Logger logger = LoggerFactory.getLogger(MoviePipeline.class);
    public final static String MOVIE_TITLE = "movie_title";
    public final static String MOVIE_INFO = "movie_info";
    public final static String MOVIE_POSTER = "movie_poster";

    @Override
    public void process(ResultItems resultItems, Task task) {
        String movieTitle = resultItems.get(MOVIE_TITLE);
        String movieInfo = resultItems.get(MOVIE_INFO);
        if (null != movieTitle) {
            Movie movie = new Movie();
            movie.setMovieName(movieTitle);
            movie.setMovieInfo(movieInfo);
            new MovieService().saveOrUpdate(movie);
        }
    }
}
