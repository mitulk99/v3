package com.amazon.exception;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionhandler {

	private static final Logger logger=LoggerFactory.getLogger(GlobalExceptionhandler.class);
	
	@ExceptionHandler(value=Exception.class)
	public String handleException(HttpServletRequest request, Exception ex)
	{
		logger.info("exception occured : " + request.getRequestURL());
		return "final";
	}
}
