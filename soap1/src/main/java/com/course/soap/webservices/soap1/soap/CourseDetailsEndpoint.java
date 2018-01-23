package com.course.soap.webservices.soap1.soap;

import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.example.myschema.CourseDetails;
import com.example.myschema.GetCourseDetailsRequest;
import com.example.myschema.GetCourseDetailsResponse;

@Endpoint
public class CourseDetailsEndpoint {
	// method
	// input GetCourseDetailsRequest 
	// output GetCourseDetailsResponse
	
	// http://alex.com/courses -- > this namespace
	// GetCourseDetailsRequest -- > this name 
	// payload root tells that it can accept the above namespace and name 
	@PayloadRoot (namespace="http://alex.com/courses", localPart="GetCourseDetailsRequest") // any payload of this type
	@ResponsePayload // convert back to XML 
	public GetCourseDetailsResponse processCourseDetailsRequest(@RequestPayload GetCourseDetailsRequest request) {
		GetCourseDetailsResponse response = new GetCourseDetailsResponse();
		
		CourseDetails courseDetails = new CourseDetails();
		courseDetails.setId(request.getId());
		courseDetails.setName("Microservices Course");
		courseDetails.setDescription("That wonderful course");
		response.setCourseDetails(courseDetails);
		
		return response;
	}
}
