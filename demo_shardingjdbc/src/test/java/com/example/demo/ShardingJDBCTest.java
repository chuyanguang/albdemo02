package com.example.demo;

import com.example.demo.entity.DictionaryDo;
import com.example.demo.entity.DictionaryKey;
import com.example.demo.entity.UserDo;
import com.example.demo.entity.dto.DeptDo;
import com.example.demo.repository.DeptRepository;
import com.example.demo.repository.DictionaryRepository;
import com.example.demo.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ShardingJDBCTest {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private DeptRepository deptRepository;
    @Autowired
    private DictionaryRepository dicRepository;

    @Test
    void test01() {
        UserDo user = new UserDo();
        user.setName("张三");
        user.setCid(12L);
        user.setAddress("南京");
        userRepository.save(user);
    }

    @Test
    void test02() {
        DeptDo detp = new DeptDo();
        detp.setCode(11L);
        detp.setName("yw");
        deptRepository.save(detp);
    }

    @Test
    void test03() {
        DictionaryKey dicKey = new DictionaryKey();
        dicKey.setType("status");
        dicKey.setCode("0");

        DictionaryDo dic = new DictionaryDo();
        dic.setDictionaryKey(dicKey);
        dic.setValue("成功");
        dic.setDescribe("状态编码");
        dicRepository.save(dic);
    }

}
