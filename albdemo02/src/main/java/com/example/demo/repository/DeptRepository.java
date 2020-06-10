package com.example.demo.repository;

import com.example.demo.entity.dto.DeptDo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DeptRepository extends JpaRepository<DeptDo, Long> {

    List<DeptDo> findByCode(String code);

}
