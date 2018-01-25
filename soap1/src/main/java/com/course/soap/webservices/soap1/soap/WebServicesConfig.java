package com.course.soap.webservices.soap1.soap;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

//enable spring web services
@EnableWs
// spring config 
@Configuration
public class WebServicesConfig {
	
	//MessageDispatcherServlet 
		// ApplicationContext
		// url -> /ws/* --> URL to expose WS
	
	@Bean
	public ServletRegistrationBean messageDispatcherServlet(ApplicationContext context) {
		// map dispatcher servlet to /ws/*
		MessageDispatcherServlet messageDispatcherServlet = new MessageDispatcherServlet();
		messageDispatcherServlet.setApplicationContext(context);
		messageDispatcherServlet.setTransformWsdlLocations(true);
		return new ServletRegistrationBean(messageDispatcherServlet, "/ws/*");
	}
	// contract based -- just include everything needed in the contract  
	
	// courses.wsdl
	// /ws/courses.wsdl
	//PortType - CoursePort 
	// Namespace - http://alex.com/courses 
	// course-details.xsd
	
	//wsdl definition
	@Bean(name="courses")
	public DefaultWsdl11Definition defaultWsdl11Definition(XsdSchema coursesSchema) {
		DefaultWsdl11Definition definition = new DefaultWsdl11Definition();
		definition.setPortTypeName("CoursePort");
		definition.setTargetNamespace("http://alex.com/courses");
		definition.setLocationUri("/ws");
		definition.setSchema(coursesSchema);
		return definition; 
	} 
	
	// define schema 
	@Bean
	public XsdSchema coursesSchema() {
		return new SimpleXsdSchema(new ClassPathResource("course-details.xsd"));
	}
	
	// 
	
}

