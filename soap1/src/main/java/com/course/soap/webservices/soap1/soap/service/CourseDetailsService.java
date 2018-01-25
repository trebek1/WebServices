package com.course.soap.webservices.soap1.soap.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Component;

import com.course.soap.webservices.soap1.soap.bean.Course;

@Component
public class CourseDetailsService {
	// get one course 
	// get all 
	// delete one 
	
	
	public enum Status { SUCCESS, FAILURE };
	
	private static List<Course> courses = new ArrayList<>();
	static {
		Course course1 = new Course(1, "spring", "10 steps");
		Course course2 = new Course(2, "Math", "Linear Algebra");
		Course course3 = new Course(3, "Science", "Chemistry");
		Course course4 = new Course(4, "Math", "Abstract Algebra");
		courses.add(course1);
		courses.add(course2);
		courses.add(course3);
		courses.add(course4);
	}
	
		
	public Course findById(int id) {
		for(Course course : courses) {
			if(course.getId() == id) {
				return course;
			}
		}
		return null;
	}
	
	public List<Course> findAll(){
		return courses;
	}
	
	public Status deleteById(int id) {
		Iterator<Course> it = courses.iterator();
		while(it.hasNext()) {
			Course course = it.next();
			if(course.getId() == id) {
				it.remove();
				return Status.SUCCESS;
			}
		}
		return Status.FAILURE;
	}
}
