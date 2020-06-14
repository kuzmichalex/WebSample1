package com.htp.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@Configuration
public class BeanConfiguration {

	@Bean
	public ConcurrentMap<String, Number> invokeCounter( )
	{
		System.out.println("invokeCounter");
		return new ConcurrentHashMap<>(); }
}
