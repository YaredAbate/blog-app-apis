package com.codewithyad.blog.execeptions;

public class ApiExceptions extends RuntimeException {

	public ApiExceptions(String message) {
		super(message);
	}

	public ApiExceptions() {
		super();
	}
	
}
