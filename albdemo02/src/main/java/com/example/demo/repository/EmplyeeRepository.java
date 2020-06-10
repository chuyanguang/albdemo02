package com.example.demo.repository;

import com.example.demo.entity.dto.EmplyeeDo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EmplyeeRepository extends JpaRepository<EmplyeeDo, Long> {

    @Query("select t from EmplyeeDo t \n" +
            "left join DeptDo d on t.code = d.code\n" +
            "where t.address like CONCAT('%',?1,'%')")
    List<EmplyeeDo> getByAddrLike(String addr);

}
