package com.example.demo.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "t_dictionary")
@Data
public class DictionaryDo implements Serializable {

    private static final long serialVersionUID = -5716273089770231211L;

    @Id
    @GeneratedValue
    @Embedded
    private DictionaryKey dictionaryKey;

    @Column(name = "dic_value")
    private String value;

    @Column(name = "dic_describe")
    private String describe;

}
