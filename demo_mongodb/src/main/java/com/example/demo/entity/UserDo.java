package com.example.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDo implements Serializable {

    private static final long serialVersionUID = 1181351625678008748L;

    private Long id;

    private String name;

    private Integer age;

}
