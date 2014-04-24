package com.googlecode.goodsamples.springsecurity.preauthentication;

public class AnnoymousPrincipal implements PreAuthenticatedPrincipal {
	@Override
	public boolean isAuthenticated() {
		return false;
	}

	@Override
	public String id() {
		return "annonymous";
	}
}
