package com.example.demo.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "dept")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeptDo implements Serializable {

    private static final long serialVersionUID = 1536857455490206715L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 主键生成策略
    private Long id;

    @Column
    private String code;

    @Column(name = "name")
    private String name;

}
