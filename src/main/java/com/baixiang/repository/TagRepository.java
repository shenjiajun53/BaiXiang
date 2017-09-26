package com.baixiang.repository;

import com.baixiang.model.MovieTag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TagRepository extends JpaRepository<MovieTag, Long> {
    MovieTag findMovieTagByTagName(String tagName);

    @Override
    List<MovieTag> findAll();
}
