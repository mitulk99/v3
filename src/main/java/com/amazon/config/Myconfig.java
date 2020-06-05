package com.amazon.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.amazon.util.Back;
import com.amazon.util.Coordinates;
import com.amazon.util.Elasticsearch;
import com.amazon.util.StoresDetails;
import com.amazon.util.UserRequirements;

@Configuration
@ComponentScan({ "com.amazon.*" })
public class Myconfig {
	
	@Bean
	public Coordinates getcoords()
	{
		return new Coordinates();
	}
	
	@Bean
	public UserRequirements getUser()
	{
		return new UserRequirements();
	}
	
	@Bean
	public StoresDetails getDetails()
	{
		return new StoresDetails();
	}
	
	@Bean
	public Back getback()
	{
		return new Elasticsearch();
	}
}
