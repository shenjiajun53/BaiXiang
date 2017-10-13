package com.baixiang.repository.mongo;

import com.baixiang.model.mongo.DoubanMovieBean;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface DoubanMovieRepository extends MongoRepository<DoubanMovieBean, String> {
    DoubanMovieBean findById(String id);
}
