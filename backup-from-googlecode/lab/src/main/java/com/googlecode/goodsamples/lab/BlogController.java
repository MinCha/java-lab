package com.googlecode.goodsamples.lab;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class BlogController {
	private BlogService blogService;
	
	@Autowired
	public BlogController(BlogService blogService) {
		this.blogService = blogService;
	}

	@RequestMapping(value = "/blog/{id}", method = {RequestMethod.GET})
	public ModelAndView section(@PathVariable String id) {
		ModelAndView result = new ModelAndView();
		result.addObject("visitors", blogService.visitorsOf(id));
		result.addObject("articles", blogService.articlesOf(id));
		result.addObject("profile", blogService.profileOf(id));
		result.addObject("comments", blogService.commentsOf(id));
		result.setViewName("blog");
		return result;
	}
}
