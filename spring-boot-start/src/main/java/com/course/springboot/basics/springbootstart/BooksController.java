package com.course.springboot.basics.springbootstart;

import java.util.List;
import java.util.Arrays;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BooksController {
	@GetMapping("/books")
	public List<Book> getAllBooks(){
		Book book = new Book(1l, "Angels and Demons", "Dan Brown");
		return Arrays.asList(book);
	}
}
