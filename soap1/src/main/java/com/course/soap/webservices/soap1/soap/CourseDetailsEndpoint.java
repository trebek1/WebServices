package com.course.soap.webservices.soap1.soap;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.course.myschema.CourseDetails;
import com.course.myschema.DeleteCourseDetailsRequest;
import com.course.myschema.DeleteCourseDetailsResponse;
import com.course.myschema.GetAllCourseDetailsRequest;
import com.course.myschema.GetAllCourseDetailsResponse;
import com.course.myschema.GetCourseDetailsRequest;
import com.course.myschema.GetCourseDetailsResponse;
import com.course.soap.webservices.soap1.soap.bean.Course;
import com.course.soap.webservices.soap1.soap.exception.CourseNotFoundException;
import com.course.soap.webservices.soap1.soap.service.CourseDetailsService;
import com.course.soap.webservices.soap1.soap.service.CourseDetailsService.Status;

@Endpoint
public class CourseDetailsEndpoint {
	
	@Autowired
	CourseDetailsService service;

	// method
	// input - GetCourseDetailsRequest
	// output - GetCourseDetailsResponse

	// http://in28minutes.com/courses
	// GetCourseDetailsRequest
	@PayloadRoot(namespace = "http://alex.com/courses", localPart = "GetCourseDetailsRequest")
	@ResponsePayload
	public GetCourseDetailsResponse processCourseDetailsRequest(@RequestPayload GetCourseDetailsRequest request) {
		Course course = service.findById(request.getId());
		if(course == null) {
			// throw new RuntimeException("Invalid course id " + request.getId());
			throw new CourseNotFoundException("Invalid course id " + request.getId());
		}
		return mapCourseDetails(course);
	}
	
	@PayloadRoot(namespace = "http://alex.com/courses", localPart = "DeleteCourseDetailsRequest")
	@ResponsePayload
	public DeleteCourseDetailsResponse deleteCourseDetailsRequest(@RequestPayload DeleteCourseDetailsRequest request) {
			Status status = service.deleteById(request.getId());
			DeleteCourseDetailsResponse response = new DeleteCourseDetailsResponse();
			response.setStatus(mapStatus(status));
		return response;
	}

	private com.course.myschema.Status mapStatus(Status status) {
		// TODO Auto-generated method stub
		if(status == Status.FAILURE) {
			return com.course.myschema.Status.FAILURE;
		}
		return com.course.myschema.Status.SUCCESS;
	}

	private GetCourseDetailsResponse mapCourseDetails(Course course) {
		GetCourseDetailsResponse response = new GetCourseDetailsResponse();
		response.setCourseDetails(mapCourse(course));
		return response;
	}

	private GetAllCourseDetailsResponse mapAllCourseDetails(List<Course> courses) {
		GetAllCourseDetailsResponse response = new GetAllCourseDetailsResponse();
		for (Course course : courses) {
			CourseDetails mapCourse = mapCourse(course);
			response.getCourseDetails().add(mapCourse);
		}
		return response;
	}

	private CourseDetails mapCourse(Course course) {
		CourseDetails courseDetails = new CourseDetails();
		courseDetails.setId(course.getId());
		courseDetails.setName(course.getName());
		courseDetails.setDescription(course.getDescription());
		return courseDetails;
	}

	@PayloadRoot(namespace = "http://alex.com/courses", localPart = "GetAllCourseDetailsRequest")
	@ResponsePayload
	public GetAllCourseDetailsResponse processAllCourseDetailsRequest(
			@RequestPayload GetAllCourseDetailsRequest request) {

		List<Course> courses = service.findAll();
		return mapAllCourseDetails(courses);
	}

}