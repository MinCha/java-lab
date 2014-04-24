package com.googlecode.goodsamples.lab;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BlogService {
	private BlogRepository blogRepository;
	
	@Autowired
	public BlogService(BlogRepository blogRepository) {
		this.blogRepository = blogRepository;
	}
	
	public List<Visitor> visitorsOf(String id) {
		return blogRepository.findAllVisitorsBy(id);
	}

	public List<Article> articlesOf(String id) {
		return blogRepository.findAllArticlesBy(id);
	}

	public Profile profileOf(String id) {
		return blogRepository.findProfileBy(id);
	}

	public List<Comment> commentsOf(String id) {
		return blogRepository.findCommentsBy(id);
	}
}
