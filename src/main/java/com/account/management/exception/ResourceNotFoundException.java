package com.account.management.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Getter
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {
	private String resourceName;
	private String filedName;
	private long filedValue;

	private String filedValue2;


	public ResourceNotFoundException(String resourceName, String filedName, String filedValue2) {
		//Message : Post not found with id : 1
		super(String.format("%s not found with %s : %s", resourceName, filedName, filedValue2));
		this.resourceName = resourceName;
		this.filedName = filedName;
		this.filedValue2 = filedValue2;
	}
	

}
