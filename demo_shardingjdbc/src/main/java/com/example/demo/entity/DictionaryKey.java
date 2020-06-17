package com.example.demo.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Data
@Embeddable
public class DictionaryKey implements Serializable {

    private static final long serialVersionUID = 4423193826197154186L;

    @Column
    private String type;

    @Column
    private String code;

}
