package com.example.demo.entity.dto;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "emplyee")
@Data
public class EmplyeeDo implements Serializable {

    private static final long serialVersionUID = -2816791408389465907L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 主键生成策略
    private Long id;

    @Column
    private String code;

    @Column(name = "name")
    private String name;

    @ManyToOne
    @JoinColumn(name = "deptcode", referencedColumnName = "code")
    private DeptDo dept;

    @Column(name = "address")
    private String address;

    @Column(name = "phone")
    private String phone;

}
