package com.example.demo.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomException extends RuntimeException {

	private static final long serialVersionUID = -1512629506795331698L;

	private int code;

	public CustomException(String errorMessage, int code) {
		super(errorMessage);
		this.code = code;
	}

}
