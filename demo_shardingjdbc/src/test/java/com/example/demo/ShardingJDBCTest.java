package com.example.demo;

import com.example.demo.entity.UserDo;
import com.example.demo.entity.dto.DeptDo;
import com.example.demo.repository.DeptRepository;
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
}
