package com.example.demo.entity.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class ThemeDo implements Serializable {

    private static final long serialVersionUID = 761937657419141385L;

    private Long id;

    private String code;

    private String name;

}
