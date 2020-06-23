package com.example.demo;

import cn.hutool.core.util.RandomUtil;
import com.example.demo.entity.UserDo;
import com.example.demo.utils.MongoUtil;
import com.google.common.collect.Lists;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import lombok.extern.slf4j.Slf4j;
import org.bson.Document;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import java.util.Collection;
import java.util.List;
import java.util.regex.Pattern;

@Slf4j
@SpringBootTest
class DemoMongodbApplicationTests {

    private final static String COLLECTION_NAME = "test01";

    @Autowired
    private MongoTemplate mongoTemplate;

    @Test
    void testInsert() {
        List<Document> list = Lists.newArrayList();
        for (int i = 0; i < 100; i++) {
            UserDo user1 = new UserDo((long) i, "test" + i, RandomUtil.randomInt(1, 100));
            list.add(MongoUtil.objToDoc(user1));
        }
        Collection<Document> collection = mongoTemplate.insert(list, UserDo.class);
//        InsertManyResult result = mongoTemplate.getCollection(COLLECTION_NAME).insertMany(list);
//        if (result.wasAcknowledged()) {
//            MongoCollection<Document> collection = mongoTemplate.getCollection(COLLECTION_NAME);
//            for (Document doc : collection.find()) {
//                UserDo userDo = MongoUtil.docToObj(doc, UserDo.class);
//                log.info(userDo.toString());
//            }
//        }
    }

    @Test
    void testQuery() {
        List<Sort.Order> orderList = Lists.newArrayList();
        orderList.add(new Sort.Order(Sort.Direction.DESC, "age"));

        Query query = new Query().skip(10).limit(10).with(Sort.by(Sort.Order.desc("age"), Sort.Order.desc("_id")));
        Pattern pattern = Pattern.compile("^.*test.*$", Pattern.CASE_INSENSITIVE);
        query.addCriteria(Criteria.where("name").regex(pattern));

        List<UserDo> userList = mongoTemplate.find(query, UserDo.class, COLLECTION_NAME);
        for (UserDo user : userList) {
            log.info(user.toString());
        }
    }

    @Test
    void testUpdate() {
//        Query query = new Query().addCriteria(Criteria.where("name").is("张三"));
//        Update update = new Update().inc("age", 2);
//        UpdateResult updateResult = mongoTemplate.updateFirst(query, update, COLLECTION_NAME);
//        if (updateResult.wasAcknowledged()){
//            log.info("update success!");
//        }

        Query query = new Query().addCriteria(Criteria.where("name").is("test7"));
        Update update = new Update().set("age", 21);
        UpdateResult updateResult = mongoTemplate.updateFirst(query, update, COLLECTION_NAME);
        if (updateResult.wasAcknowledged()) {
            log.info("update success!");
        }
    }

    @Test
    void testDel() {
        Query query = new Query().addCriteria(Criteria.where("name").is("张华"));
        DeleteResult deleteResult = mongoTemplate.remove(query, COLLECTION_NAME);
        if (deleteResult.wasAcknowledged()) {
            log.info("delete success!");
        }
    }

}
