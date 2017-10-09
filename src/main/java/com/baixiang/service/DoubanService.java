package com.baixiang.service;

import com.baixiang.model.Movie;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoubanService {
    private static final Logger logger = LoggerFactory.getLogger(DoubanService.class);
    @Autowired
    private MovieService movieService;
    private int currentNum = 0;

    public void startDoubanPatch() {
        Pageable pageable = new PageRequest(currentNum, 40);
        Page<Movie> moviePage = movieService.getByPage(pageable);
        for (Movie movie : moviePage) {
            logger.info("douban Movie=" + movie.toString());
        }
    }
}
