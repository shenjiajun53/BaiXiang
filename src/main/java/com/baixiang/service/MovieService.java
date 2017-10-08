package com.baixiang.service;

import com.baixiang.model.Actor;
import com.baixiang.model.Movie;
import com.baixiang.model.MovieTag;
import com.baixiang.repository.ActorRepository;
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
    @Autowired
    private ActorRepository actorRepository;
    @Autowired
    private TagService tagService;

    public Movie save(Movie movie) {
        movieRepository.saveAndFlush(movie);
        return movie;
    }

    public void delete(long id) {
//        Movie movie=movieRepository.getById(id);
//        movie.getActorSet().clear();
//        movie.getMovieTagSet().clear();
//        movie.getMovieTorrents().clear();
//        movie.getScreenShots().clear();
        movieRepository.delete(id);
    }


//    public Movie update(Movie movie) {
//        movieRepository.save(movie);
//        return movie;
//    }

    public List<Movie> getAll() {
        return movieRepository.findAll();
    }

    public List<Movie> getNewest() {
        return movieRepository.findTop20ByOrderByIdDesc();
    }

    public Movie getById(long id) {
        return movieRepository.getById(id);
    }

    public List<Movie> getByName(String movieName) {
        return movieRepository.getByMovieName(movieName);
    }

    public Page<Movie> getByTag(String tag, Pageable pageable) {
        MovieTag movieTag = tagService.getTagByName(tag);
        if (null == movieTag) {
            return null;
        }
        return movieRepository.getByMovieTagSetIn(movieTag, pageable);
    }

    public Page<Movie> getByActor(String actorName, Pageable pageable) {
        Actor actor = actorRepository.findActorByActorName(actorName);
        if (null == actor) {
            return null;
        }
        return movieRepository.getByActorSetIn(actor, pageable);
    }

    public List<Movie> getHostest() {
        return movieRepository.findTop15ByOrderByViewTimes();
    }

    public int getSizeByTag(String tag) {
        MovieTag movieTag = tagService.getTagByName(tag);
        if (null == movieTag) {
            return 0;
        }
        return movieRepository.getByMovieTagSetIn(movieTag).size();
    }

    public int getSizeByActor(String actorName) {
        Actor actor = actorRepository.findActorByActorName(actorName);
        if (null == actor) {
            return 0;
        }
        return movieRepository.getByActorSetIn(actor).size();
    }


    public List<Movie> getIncludeName(String nameStr) {
        return movieRepository.getByMovieNameContaining(nameStr);
    }

//    public List<Movie> getIncludeName(String movieName) {
//        return movieRepository.getIncludeName(movieName);
//    }
}
