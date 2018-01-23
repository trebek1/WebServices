package com.course.springboot.basics.springbootstart;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class SpringBootStartApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext applicationContext = SpringApplication.run(SpringBootStartApplication.class, args);
		
//		for(String string : applicationContext.getBeanDefinitionNames()) {
//			System.out.println(string);
//		}
		
	}
}
