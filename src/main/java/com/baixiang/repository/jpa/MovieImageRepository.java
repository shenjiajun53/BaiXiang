package com.baixiang.repository.jpa;

import com.baixiang.model.jpa.Movie;
import com.baixiang.model.jpa.MovieImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieImageRepository extends JpaRepository<MovieImage, Long> {

}
