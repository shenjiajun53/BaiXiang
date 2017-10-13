package com.baixiang.service;

import com.baixiang.model.jpa.MovieTag;
import com.baixiang.repository.jpa.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagService {
    @Autowired
    private TagRepository tagRepository;

    public MovieTag getMovieTagUnique(String tagName) {
        MovieTag movieTag = getTagByName(tagName);
        if (null == movieTag) {
            movieTag = new MovieTag(tagName);
            save(movieTag);
        }
        return movieTag;
    }

    public MovieTag getTagByName(String tagName) {
        return tagRepository.findMovieTagByTagName(tagName);
    }

    public MovieTag save(MovieTag movieTag) {
        return tagRepository.saveAndFlush(movieTag);
    }

    public List<MovieTag> findAll() {
        return tagRepository.findAll();
    }
}
