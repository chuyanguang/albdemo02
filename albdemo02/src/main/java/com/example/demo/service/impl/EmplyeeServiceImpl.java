package com.example.demo.service.impl;

import com.example.demo.component.exception.BusinessException;
import com.example.demo.entity.dto.EmplyeeDo;
import com.example.demo.repository.EmplyeeRepository;
import com.example.demo.service.EmplyeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
public class EmplyeeServiceImpl implements EmplyeeService {

    @Autowired
    private EmplyeeRepository emplyeeRepository;

    @Override
    public List<EmplyeeDo> getByAddressLike(String addr) {
        log.info("addr={}", addr);
        return emplyeeRepository.getByAddrLike(addr);
    }

    @Override
    public EmplyeeDo getByName(String name) {
        return emplyeeRepository.getByName(name);
    }

    @Transactional
    @Override
    public void updatePhoneByCode(String code, String phone) {
        int result = emplyeeRepository.updatePhoneByCode(code, phone);
        if (result < 1){
            throw new BusinessException("更新员工信息失败");
        }
    }
}
