package com.googlecode.goodsamples.lab;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

@Component
public class UserInterceptor extends HandlerInterceptorAdapter {
	private static final Log LOG = LogFactory.getLog(UserInterceptor.class);
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		LOG.info("Check whether an User or Guest");
		String id = request.getHeader("id");
		
		if (StringUtils.isNotEmpty(id)) {
			request.setAttribute("user", new User(id));
		}
		
		return super.preHandle(request, response, handler);
	}
}
