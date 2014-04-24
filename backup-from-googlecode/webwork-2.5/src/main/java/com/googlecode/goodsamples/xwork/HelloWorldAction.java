package com.googlecode.goodsamples.xwork;

import com.opensymphony.xwork.ActionSupport;

@SuppressWarnings("serial")
public class HelloWorldAction extends ActionSupport {
	private String message;
	private String name = "guest";

	public String execute() {
		message = "Hello " + name + "~ ";
		return "success";
	}

	public String getMessage() {
		return message;
	}
	
	public void setName(String name) {
		this.name = name;
	}
}
