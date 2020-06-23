package com.example.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "test01")
public class UserDo implements Serializable {

    private static final long serialVersionUID = 1181351625678008748L;

    @Id
    @Field(name = "_id")
    private Long _id;

    @Field(name = "name")
    private String name;

    @Field(name = "age")
    private Integer age;

    public UserDo(String name, Integer age) {
        this.name = name;
        this.age = age;
    }
}
