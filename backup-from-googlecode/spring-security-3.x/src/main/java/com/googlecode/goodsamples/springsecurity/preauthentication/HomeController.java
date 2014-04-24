package com.googlecode.goodsamples.springsecurity.preauthentication;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {
	@RequestMapping(value = "/home", method = {RequestMethod.GET})
	public ModelAndView home() {
		ModelAndView result = new ModelAndView();
		result.setViewName("home");
		return result;
	}
}
