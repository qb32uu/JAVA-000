package com.training.week05.bean;


import java.io.Serializable;

import org.springframework.stereotype.Service;

import com.sun.xml.internal.ws.api.streaming.XMLStreamReaderFactory.Default;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Data
@AllArgsConstructor
@ToString
@Service
public class Student implements Serializable {
    private static final long serialVersionUID = -3627952443136359161L;
    private int id;
    private String name;
    
    public Student() {
        id = -1;
        name = "default";
    }
    public void init(){
        System.out.println("hello...........");
    }
    
//    public Student create(){
//        return new Student(-1,"default");
//    }
}
