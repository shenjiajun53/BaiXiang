package com.baixiang.service;

import com.baixiang.controller.MovieController;
import com.baixiang.model.Movie;
import com.baixiang.repository.MovieRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

/**
 * Created by shenjj on 2017/6/21.
 */

@Service
@Configurable
//@ComponentScan(value = "com.baixiang")
public class MovieService {
    @Autowired
    private MovieRepository movieRepository;

    public void save(Movie movie) {
        movieRepository.save(movie);
        return;
    }


    public void update(Movie movie) {
        movieRepository.update(movie);
        return;
    }

    public void saveOrUpdate(Movie movie) {
        movieRepository.saveOrUpdate(movie);
        return;
    }
}
