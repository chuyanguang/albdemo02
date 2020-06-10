package com.example.demo.controller;

import com.example.demo.entity.ResultData;
import com.example.demo.entity.dto.DeptDo;
import com.example.demo.entity.dto.EmplyeeDo;
import com.example.demo.service.DeptService;
import com.example.demo.service.EmplyeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("emplyee")
public class EmplyeeController {

    @Autowired
    private EmplyeeService emplyeeService;

    @GetMapping(value = "getByAddr")
    public ResultData<List<DeptDo>> getByAddress(String addr){
        List<EmplyeeDo> empList = emplyeeService.getByAddressLike(addr);
        return ResultData.ok("查询成功", empList);
    }

}
