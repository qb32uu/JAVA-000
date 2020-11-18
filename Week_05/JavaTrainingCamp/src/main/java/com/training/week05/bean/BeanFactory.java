package com.training.week05.bean;

public class BeanFactory {
    public static Student createStudentStatic() {
        return new Student(2, "factory");
    }
    

    public  Student createStudent() {
        return new Student(2, "factory");
    }
}
