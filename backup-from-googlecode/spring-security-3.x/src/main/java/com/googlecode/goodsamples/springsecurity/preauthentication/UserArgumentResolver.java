package com.googlecode.goodsamples.springsecurity.preauthentication;

import org.springframework.core.MethodParameter;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.support.WebArgumentResolver;
import org.springframework.web.context.request.NativeWebRequest;

public class UserArgumentResolver implements WebArgumentResolver {
	@Override
	public Object resolveArgument(MethodParameter methodParameter,
			NativeWebRequest webRequest) throws Exception {
		if (methodParameter.getParameterType().isAssignableFrom(User.class) == false) {
			return UNRESOLVED;
		}
		
		return (User) SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();
		
	}
}
