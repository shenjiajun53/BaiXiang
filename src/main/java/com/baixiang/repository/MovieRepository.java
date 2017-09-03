package com.baixiang.repository;

import com.baixiang.model.Actor;
import com.baixiang.model.Movie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by shenjj on 2017/7/12.
 */

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {
    Movie getById(Long id);

    Page<Movie> getByMovieTagSetIn(String tag, Pageable pageable);

    Page<Movie> getByActorSetIn(Actor actor, Pageable pageable);

    List<Movie> getByMovieTagSetIn(String tag);

    List<Movie> getByActorSetIn(Actor actor);

//    @Query("select m from Movie  ")
//    int getSizeByTag(String tag);

    List<Movie> getByMovieName(String movieName);

    //    @Query("select m from Movie as m order by m.viewTimes desc limit 0, 25")
    List<Movie> findTop15ByOrderByViewTimes();

    List<Movie> findTop20ByOrderById();

    List<Movie> findTop20ByOrderByIdDesc();

    List<Movie> getByMovieNameContaining(String nameStr);
}
