package com.example.demo.repository;

import com.example.demo.entity.DictionaryDo;
import org.springframework.data.repository.CrudRepository;

public interface DictionaryRepository extends CrudRepository<DictionaryDo, Integer> {

}
