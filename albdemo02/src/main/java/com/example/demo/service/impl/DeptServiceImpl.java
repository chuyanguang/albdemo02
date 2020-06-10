package com.example.demo.service.impl;

import com.example.demo.entity.dto.DeptDo;
import com.example.demo.repository.DeptRepository;
import com.example.demo.service.DeptService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class DeptServiceImpl implements DeptService {

    @Autowired
    private DeptRepository deptRepository;

    @Override
    public List<DeptDo> getDeptByCode(String code) {
        log.info("code={}", code);
        return deptRepository.findByCode(code);
    }

}
