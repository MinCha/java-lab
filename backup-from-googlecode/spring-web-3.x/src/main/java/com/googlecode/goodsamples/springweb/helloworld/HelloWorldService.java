package com.googlecode.goodsamples.springweb.helloworld;

import org.springframework.stereotype.Service;

@Service
public class HelloWorldService {
	public String makeMessage() {
		return "Hello " + System.currentTimeMillis();
	}
}
