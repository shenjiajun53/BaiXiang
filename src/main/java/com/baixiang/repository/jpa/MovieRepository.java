package com.baixiang.repository.jpa;

import com.baixiang.model.jpa.Actor;
import com.baixiang.model.jpa.Movie;
import com.baixiang.model.jpa.MovieTag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by shenjj on 2017/7/12.
 */

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {
    Movie getById(Long id);

    Page<Movie> findAll(Pageable pageable);

    Page<Movie> getByMovieTagSetIn(MovieTag tag, Pageable pageable);

    Page<Movie> getByActorSetIn(Actor actor, Pageable pageable);

    List<Movie> getByMovieTagSetIn(MovieTag tag);

    List<Movie> getByActorSetIn(Actor actor);

//    @Query("select m from Movie  ")
//    int getSizeByTag(String tag);

    List<Movie> getByMovieName(String movieName);

    //    @Query("select m from Movie as m order by m.viewTimes desc limit 0, 25")
    List<Movie> findTop15ByOrderByViewTimes();

    List<Movie> findTop20ByOrderById();

    List<Movie> findTop20ByOrderByIdDesc();

    List<Movie> getByMovieNameContaining(String nameStr);

    List<Movie> getTop10ByMovieNameContaining(String nameStr);
}
