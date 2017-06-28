package com.baixiang;

import com.baixiang.config.MongoConfig;
import com.mongodb.WriteResult;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by shenjiajun on 2017/4/3.
 */

@RunWith(SpringRunner.class)
public class MongoTest {
    @Test
    public void insert() {
        AnnotationConfigApplicationContext a = new AnnotationConfigApplicationContext(MongoConfig.class);
        MongoOperations mongoOps = a.getBean(MongoOperations.class);
        Person person = new Person("white", 23);
        // 单条插入
        mongoOps.insert(person);
        List<Person> persons = new ArrayList<Person>();
        for (int i = 1; i <= 20; i++) {
            persons.add(new Person("white" + i + "号", 20 + i));
        }
        // 批量插入
        mongoOps.insertAll(persons);
        a.close();
    }

    @Test
    public void query() {
        AnnotationConfigApplicationContext a = new AnnotationConfigApplicationContext(MongoConfig.class);
        MongoOperations mongoOps = a.getBean(MongoOperations.class);

        // 查询匹配条件的第一条数据
        Person findOne = mongoOps.findOne(new Query(Criteria.where("name").is("white")), Person.class);
        System.out.println(findOne.toString());

        // 查询所有记录
        List<Person> all = mongoOps.findAll(Person.class);
        System.out.println(all);

        // 查询age >= 25 and age < 30
        Query query = new Query(Criteria.where("age").gte(25).lt(30));
        List<Person> findByCondition = mongoOps.find(query, Person.class);
        System.out.println(findByCondition);

        // 原生命令方式
        BasicQuery bq = new BasicQuery("{name:'white'}");
        Person findOne2 = mongoOps.findOne(bq, Person.class);
        System.out.println(findOne2);
        a.close();
    }

    @Test
    public void delete() {
        AnnotationConfigApplicationContext a = new AnnotationConfigApplicationContext(MongoConfig.class);
        MongoOperations mongoOps = a.getBean(MongoOperations.class);

        // 删除age>=25的所有记录
        WriteResult remove = mongoOps.remove(new Query(Criteria.where("age").gte(25)), Person.class);

        // 查出第一条匹配的记录并把这条记录删除 阅后即焚
        Person findAndRemove = mongoOps.findAndRemove(new Query(Criteria.where("name").is("white")), Person.class);
        System.out.println(findAndRemove);

        // 按对象删除
        Person p = mongoOps.findOne(new Query(Criteria.where("age").is(22)), Person.class);
        mongoOps.remove(p);

        // 删除集合
        mongoOps.dropCollection(Person.class);
        a.close();
    }

    @Test
    public void updateTest() {
        AnnotationConfigApplicationContext a = new AnnotationConfigApplicationContext(MongoConfig.class);
        MongoOperations mongoOps = a.getBean(MongoOperations.class);

        // 更新一条
        mongoOps.updateFirst(new Query(Criteria.where("age").is(23)), Update.update("name", "white小哥"), Person.class);

        // 更新多条
        mongoOps.updateMulti(new Query(Criteria.where("age").lte(23)), Update.update("name", "white大哥"), Person.class);
        a.close();
    }
}
