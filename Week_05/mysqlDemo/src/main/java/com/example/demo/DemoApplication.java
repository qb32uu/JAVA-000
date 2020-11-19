package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
	    System.out.println("测试访问http://localhost:18080/");
		SpringApplication.run(DemoApplication.class, args);
	}

}
