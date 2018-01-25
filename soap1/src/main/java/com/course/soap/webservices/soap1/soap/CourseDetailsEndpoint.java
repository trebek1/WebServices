package com.course.soap.webservices.soap1.soap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.course.myschema.CourseDetails;
import com.course.myschema.GetCourseDetailsRequest;
import com.course.myschema.GetCourseDetailsResponse;
import com.course.soap.webservices.soap1.soap.bean.Course;
import com.course.soap.webservices.soap1.soap.service.CourseDetailsService;

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
		GetCourseDetailsResponse response = new GetCourseDetailsResponse();
		Course course = service.findById(request.getId());
		CourseDetails courseDetails = new CourseDetails();
		courseDetails.setId(course.getId());
		courseDetails.setName(course.getName());
		courseDetails.setDescription(course.getDescription());
		
		response.setCourseDetails(courseDetails);
		
		return response;
	}

}