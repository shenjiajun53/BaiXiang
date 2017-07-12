package com.baixiang.service;

import com.baixiang.model.Movie;
import com.baixiang.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.List;

/**
 * Created by shenjj on 2017/6/21.
 */

@Service
//@ComponentScan(value = "com.baixiang")
public class MovieService {
    @Autowired
    EntityManager entityManager;

    @Autowired
    private MovieRepository movieRepository;

    public Movie save(Movie movie) {
        movieRepository.saveAndFlush(movie);
        return movie;
    }


//    public Movie update(Movie movie) {
//        movieRepository.save(movie);
//        return movie;
//    }

    public List<Movie> getAll() {
        return movieRepository.findAll();
    }

    public List<Movie> getNewest() {
        return movieRepository.findTop20ByOrderById();
    }

    public Movie getById(long id) {
        return movieRepository.getById(id);
    }

    public Page<Movie> getByTag(String tag, Pageable pageable) {
        return movieRepository.getByMovieTagSetIn(tag, pageable);
    }

    public List<Movie> getHostest() {
        return movieRepository.findTop15ByOrderByViewTimes();
    }

    public int getSizeByTag(String tag) {
        return movieRepository.getByMovieTagSetIn(tag).size();
    }


    public List<Movie> getIncludeName(String nameStr) {
        return movieRepository.getByMovieNameContaining(nameStr);
    }

//    public List<Movie> getIncludeName(String movieName) {
//        return movieRepository.getIncludeName(movieName);
//    }
}
