package com.course.soap.webservices.soap1.soap;

import java.util.Collections;
import java.util.List;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.ws.server.EndpointInterceptor;
import org.springframework.ws.soap.security.xwss.XwsSecurityInterceptor;
import org.springframework.ws.soap.security.xwss.callback.SimplePasswordValidationCallbackHandler;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

//enable spring web services
@EnableWs
// spring config 
@Configuration
public class WebServicesConfig extends WsConfigurerAdapter {
	
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
	
	@Bean
	public XwsSecurityInterceptor securityInterceptor() {
		XwsSecurityInterceptor securityInterceptor = new XwsSecurityInterceptor();
		securityInterceptor.setCallbackHandler(callbackHandler());
		securityInterceptor.setPolicyConfiguration(new ClassPathResource("securityPolicy.xml"));
		
		return securityInterceptor;
	}
	
	@Bean
	public SimplePasswordValidationCallbackHandler callbackHandler() {
		SimplePasswordValidationCallbackHandler handler = new SimplePasswordValidationCallbackHandler();
		handler.setUsersMap(Collections.singletonMap("user", "password"));
		return handler;
	}
	@Override
	public void addInterceptors(List<EndpointInterceptor> interceptors) {
		interceptors.add(securityInterceptor());
	}
	
	// XwsSecurityInterceptor
		// Callback Handler -> check UserId and Password (SimplePasswordValidationCallbackHandler
		// Seurity Policy -> securityPolicy.xml
	// Interceptors.add -> xwsSecurityInterceptor
	
	
}

