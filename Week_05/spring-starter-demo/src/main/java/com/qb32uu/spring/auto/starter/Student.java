package com.qb32uu.spring.auto.starter;


import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class Student implements Serializable {
    
    private int id;
    private String name;
    
    public void init(){
        System.out.println("hello...........");
    }
    
    public static Student create(){
        return new Student(101,"KK101");
    }
}
