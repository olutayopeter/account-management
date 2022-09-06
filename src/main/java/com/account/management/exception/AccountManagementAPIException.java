package com.account.management.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class AccountManagementAPIException extends RuntimeException {
	private HttpStatus status;
	private String message;

	
	public AccountManagementAPIException(String message, HttpStatus status, String message1) {
		super(message);
		this.status = status;
		this.message = message1;
	}


}
