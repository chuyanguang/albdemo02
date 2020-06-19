package com.example.demo;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.example.demo.entity.UserDo;
import com.example.demo.utils.MongoUtil;
import com.mongodb.client.MongoCollection;
import lombok.extern.slf4j.Slf4j;
import org.bson.Document;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;

@Slf4j
@SpringBootTest
class DemoMongodbApplicationTests {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Test
    void test01(){
        String collectionName = "test01";
        UserDo user = new UserDo();
        user.setName("张华");
        user.setAge(17);

        mongoTemplate.getCollection(collectionName).insertOne(MongoUtil.objToDoc(user));
        MongoCollection<Document> collection = mongoTemplate.getCollection(collectionName);
        for (Document doc : collection.find()) {
            UserDo userDo = MongoUtil.docToObj(doc, UserDo.class);
            log.info(userDo.getName());
        }
    }

}
