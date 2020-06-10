package com.example.demo.controller;

import com.example.demo.entity.ResultData;
import com.example.demo.entity.dto.DeptDo;
import com.example.demo.service.DeptService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("dept")
public class DeptController {

    @Autowired
    private DeptService deptService;

    @GetMapping(value = "getByCode")
    public ResultData<List<DeptDo>> getByCodes(String code){
        List<DeptDo> deptList = deptService.getDeptByCode(code);
        return ResultData.ok("查询成功", deptList);
    }

}
