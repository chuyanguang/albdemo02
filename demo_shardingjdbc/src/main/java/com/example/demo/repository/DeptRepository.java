package com.example.demo.repository;

import com.example.demo.entity.dto.DeptDo;
import org.springframework.data.repository.CrudRepository;

public interface DeptRepository extends CrudRepository<DeptDo, Long> {

}
