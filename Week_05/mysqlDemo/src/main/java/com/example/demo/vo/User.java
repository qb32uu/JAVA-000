package com.example.demo.vo;

import lombok.Data;

@Data
public class User {
    private int id;
    private String name;
    private int age;

    public User(String name, int age) {
        super();
        this.name = name;
        this.age = age;
    }

    public User() {
        super();
    }

    public User(int id, String name, int age) {
        super();
        this.id = id;
        this.name = name;
        this.age = age;
    }

}
