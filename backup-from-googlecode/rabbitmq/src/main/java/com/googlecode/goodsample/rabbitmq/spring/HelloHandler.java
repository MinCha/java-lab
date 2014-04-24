package com.googlecode.goodsample.rabbitmq.spring;

import org.springframework.stereotype.Component;

@Component
public class HelloHandler {
	private static int string = 0;
	private static int object = 0;

	public void handleString(String message) {
		string++;
		System.out.println("String : " + message + " " + string);
	}

	public void handleObject(Pick pick) {
		object++;
		System.out.println("Pick : " + pick + " " + object);
	}
}
