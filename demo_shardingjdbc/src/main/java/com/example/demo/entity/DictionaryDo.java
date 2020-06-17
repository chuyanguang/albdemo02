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
    @Embedded
    private DictionaryKey dictionaryKey;

    @Column
    private String value;

    @Column
    private String describe;

}
