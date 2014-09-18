package com.github.pagenavigation;

@SuppressWarnings("serial")
public class CannotMakePageUrlException extends RuntimeException {
	public CannotMakePageUrlException(String reason, Throwable cause) {
		super(reason, cause);
	}
}
