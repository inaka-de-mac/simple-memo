package com.example.simplememo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.example.simplememo.mapper")
public class SimpleMemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SimpleMemoApplication.class, args);
	}

}
