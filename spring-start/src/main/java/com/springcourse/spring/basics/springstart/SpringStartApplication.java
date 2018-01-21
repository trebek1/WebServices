package com.springcourse.spring.basics.springstart;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class SpringStartApplication {
	// what beans does spring need to manage 
	// what are the dependencies for a bean -- (sort for BinarySearchImpl) 
	// where to search for beans -- what package  
	
	public static void main(String[] args) {
		// will come from spring 
		// Spring Application Context will manage bean 
		// BinarySearchImpl binarySearch = new BinarySearchImpl(new BubbleSortAlgorithm());
		
		ConfigurableApplicationContext applicationContext = SpringApplication.run(SpringStartApplication.class, args);
		
		// now get the bean from the application context 
		BinarySearchImpl binarySearch = applicationContext.getBean(BinarySearchImpl.class);
		
		int result = binarySearch.binarySearch(new int[] {12, 4, 6}, 3);
		
		System.out.println(result);
		
		
		

	}
}
