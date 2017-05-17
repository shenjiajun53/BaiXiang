package com.baixiang.config;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

/**
 * Created by shenjiajun on 2017/4/3.
 */

@Configuration
@EnableMongoRepositories(basePackages = "com.baixiang")
public class MongoConfig extends AbstractMongoConfiguration {


    @Override
    protected String getDatabaseName() {
        return "BaiXiang";
    }

    @Override
    public Mongo mongo() throws Exception {
        return new MongoClient();
    }


//    @Bean
//    public MongoClientFactoryBean mongo() {
//        MongoClientFactoryBean factoryBean = new MongoClientFactoryBean();
//        factoryBean.setHost("localhost"); // 数据库地址
//        factoryBean.setPort(27017); // 端口
//        return factoryBean;
//    }

//    @Bean
//    public MongoOperations mongoTemplate(Mongo mongo) {
//        // 操作Mongo的模板类，提供了非常纯粹的oo操作数据库的api
//        return new MongoTemplate(mongo, "oneblog"); // dbtest 为数据库名
//    }
}
