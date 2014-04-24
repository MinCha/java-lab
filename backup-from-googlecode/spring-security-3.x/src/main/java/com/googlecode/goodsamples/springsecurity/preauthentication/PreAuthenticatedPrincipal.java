package com.googlecode.goodsamples.springsecurity.preauthentication;

public interface PreAuthenticatedPrincipal {
	boolean isAuthenticated();
	String id();
}
