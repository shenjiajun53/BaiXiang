package com.baixiang.spider;

import com.baixiang.model.Film;
import com.baixiang.repository.FilmRepository;
import com.baixiang.spider.pipeline.MoviePipeline;
import org.apache.http.util.TextUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

/**
 * Created by Administrator on 2017/7/1.
 */

@Service
public class FilmPipeline implements Pipeline {
    private static final Logger logger = LoggerFactory.getLogger(MoviePipeline.class);
    public final static String FILM_TITLE = "film_title";
    public final static String FILM_INFO = "film_info";
    public final static String FILM_URL = "film_poster";

    @Autowired
    private FilmRepository filmRepository;

    @Override
    public void process(ResultItems resultItems, Task task) {
        String filmTitle = resultItems.get(FILM_TITLE);
        String filmInfo = resultItems.get(FILM_INFO);
        String filmUrl = resultItems.getRequest().getUrl();
        if (filmUrl.contains("http://thzav.com/thread") && !TextUtils.isEmpty(filmTitle) && !TextUtils.isEmpty(filmInfo)) {
            Film film = new Film();
            film.setFilmInfo(filmInfo);
            film.setFilmUrl(filmUrl);
            film.setFilmTitle(filmTitle);
            filmRepository.save(film);
        }
    }


}
