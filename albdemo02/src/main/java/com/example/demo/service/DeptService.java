package com.example.demo.service;

import com.example.demo.entity.dto.DeptDo;

import java.util.List;

public interface DeptService {

    List<DeptDo> getDeptByCode(String code);

}
