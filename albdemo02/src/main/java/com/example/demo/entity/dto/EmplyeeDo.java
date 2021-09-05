package com.example.demo.entity.dto;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelIgnore;
import cn.afterturn.easypoi.excel.annotation.ExcelTarget;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@ExcelTarget("emplyee")
@Entity
@Table(name = "emplyee")
@Data
public class EmplyeeDo implements Serializable {

    private static final long serialVersionUID = -2816791408389465907L;

    @ExcelIgnore
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 主键生成策略
    private Long id;

    @ExcelIgnore
    @Column
    private String code;

    @Excel(name = "姓名", orderNum = "0")
    @Column(name = "name")
    private String name;

    @ExcelIgnore
    @ManyToOne
    @JoinColumn(name = "deptcode", referencedColumnName = "code")
    private DeptDo dept;

    @Excel(name = "地址")
    @Column(name = "address")
    private String address;

    @ExcelIgnore
    @Column(name = "phone")
    private String phone;

    @ExcelIgnore
    @Temporal(TemporalType.DATE)
    @Column(name = "birth")
    private Date birth;

}
