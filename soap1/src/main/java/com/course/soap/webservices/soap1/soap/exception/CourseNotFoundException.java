package com.course.soap.webservices.soap1.soap.exception;

import org.springframework.ws.soap.server.endpoint.annotation.FaultCode;
import org.springframework.ws.soap.server.endpoint.annotation.SoapFault;

@SoapFault(faultCode = FaultCode.CUSTOM, customFaultCode = "{http://alex.com/courses}Alex Error")
public class CourseNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1483493448002539770L;

	public CourseNotFoundException(String message) {
		super(message);
		
	}
	// hates checked exceptions so dont use Exception
	
	

}
