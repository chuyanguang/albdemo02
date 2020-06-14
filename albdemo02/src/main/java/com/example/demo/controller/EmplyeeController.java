package com.example.demo.controller;

import com.example.demo.entity.ResultData;
import com.example.demo.entity.dto.DeptDo;
import com.example.demo.entity.dto.EmplyeeDo;
import com.example.demo.service.EmplyeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("emplyee")
public class EmplyeeController {

    @Autowired
    private EmplyeeService emplyeeService;

    @GetMapping(value = "getByAddr")
    public ResultData<List<DeptDo>> getByAddress(String addr) {
        List<EmplyeeDo> empList = emplyeeService.getByAddressLike(addr);
        return ResultData.ok("查询成功", empList);
    }

    @GetMapping(value = "getByName")
    public ResultData<List<DeptDo>> getByName(String name) {
        EmplyeeDo emplyeeDo = emplyeeService.getByName(name);
        return ResultData.ok("查询成功", emplyeeDo);
    }

    @PostMapping(value = "updatePhoneByCode")
    public ResultData updatePhoneByCode(String code, String phone) {
        emplyeeService.updatePhoneByCode(code, phone);
        return ResultData.ok("更新成功", null);
    }

    @PostMapping("save")
    public ResultData save(@RequestBody EmplyeeDo emp){
        emplyeeService.create(emp);
        return ResultData.ok("添加成功", null);
    }

}
