package com.googlecode.goodsamples.springsecurity.preauthentication;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class SecuredHomeController {
	@RequestMapping(value = "/secured/home", method = {RequestMethod.GET})
	public ModelAndView securedHome(User user) {
		ModelAndView result = new ModelAndView();
		result.setViewName("securedHome");
		result.addObject("user", user);
		return result;
	}
}
