package com.baixiang.service;

import com.baixiang.model.mongo.DoubanMovieBean;
import com.baixiang.repository.mongo.DoubanMovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DoubanMovieService {
    @Autowired
    DoubanMovieRepository doubanMovieRepository;

    public DoubanMovieBean save(DoubanMovieBean doubanMovieBean) {
        return doubanMovieRepository.save(doubanMovieBean);
    }
}
