package com.googlecode.goodsamples.springsecurity.preauthentication;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.web.authentication.preauth.AbstractPreAuthenticatedProcessingFilter;

public class PreAuthenticatedProcessingFilter extends AbstractPreAuthenticatedProcessingFilter{
	public PreAuthenticatedProcessingFilter(AuthenticationManager authenticationManager) {
		super.setAuthenticationManager(authenticationManager);
		super.setContinueFilterChainOnUnsuccessfulAuthentication(false);
	}
	
	@Override
	protected Object getPreAuthenticatedPrincipal(HttpServletRequest request) {
		if (request.getParameterMap().containsKey("id")) {
			return new IdentifiedPrincipal(request.getParameter("id"));
		} else {
			return new AnnoymousPrincipal();
		}
	}

	@Override
	protected Object getPreAuthenticatedCredentials(HttpServletRequest request) {
		final String dummy = "";  
		return dummy;
	}
}
