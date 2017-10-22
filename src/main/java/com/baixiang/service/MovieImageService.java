package com.baixiang.service;

import com.baixiang.model.jpa.MovieImage;
import com.baixiang.repository.jpa.MovieImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MovieImageService {
    @Autowired
    private MovieImageRepository movieImageRepository;

    public MovieImage save(MovieImage movieImage) {
        return movieImageRepository.save(movieImage);
    }
}
