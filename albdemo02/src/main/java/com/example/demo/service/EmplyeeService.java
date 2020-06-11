package com.example.demo.service;

import com.example.demo.entity.dto.EmplyeeDo;

import java.util.List;

public interface EmplyeeService {

    List<EmplyeeDo> getByAddressLike(String addr);

    EmplyeeDo getByName(String name);

    void updatePhoneByCode(String code, String phone);

}
