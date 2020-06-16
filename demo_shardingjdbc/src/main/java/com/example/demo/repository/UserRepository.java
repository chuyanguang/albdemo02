package com.example.demo.repository;

import com.example.demo.entity.UserDo;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<UserDo, Long> {

}
