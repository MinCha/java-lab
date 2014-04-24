package com.googlecode.goodsamples.springweb.helloworld;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HelloWorldController {
	private HelloWorldService helloWorldService;

	@Autowired
	public HelloWorldController(HelloWorldService helloWorldService) {
		this.helloWorldService = helloWorldService;
	}

	@RequestMapping(value = "/hello", method = {RequestMethod.GET})
	public ModelAndView hello(String name) {
		ModelAndView result = new ModelAndView();
		result.addObject(helloWorldService.makeMessage());
		result.addObject("name", name);
		return result;
	}
}
