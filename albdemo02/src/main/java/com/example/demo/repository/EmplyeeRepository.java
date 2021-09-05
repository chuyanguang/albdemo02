package com.example.demo.repository;

import com.example.demo.entity.dto.EmplyeeDo;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface EmplyeeRepository extends CrudRepository<EmplyeeDo, Long> {

    @Query("select t from EmplyeeDo t left join DeptDo d on t.code = d.code where t.address like CONCAT('%',?1,'%')")
    List<EmplyeeDo> getByAddrLike(String addr);

    @Query(value = "select t.id,t.name,t.code,t.deptcode,t.address,t.phone from emplyee t where t.name = :name", nativeQuery = true)
    EmplyeeDo getByName(String name);

    @Modifying
    @Query(value = "update EmplyeeDo t set t.phone = ?2 where t.code = ?1")
    int updatePhoneByCode(String code, String phone);

}