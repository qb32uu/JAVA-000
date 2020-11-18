package com.qb32uu.bean;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qb32uu.spring.auto.starter.Student;

@RestController
public class DemoController {

   @Autowired
   private Student student;

   @GetMapping("/")
   public String index() {
       return student.toString();
   }
}
