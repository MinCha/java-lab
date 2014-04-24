package com.googlecode.goodsamples.springsecurity.preauthentication;

public class IdentifiedPrincipal implements PreAuthenticatedPrincipal {
	private String id;
	
	public IdentifiedPrincipal(String id) {
		this.id = id;
	}

	@Override
	public boolean isAuthenticated() {
		return true;
	}
	
	public String id() {
		return id;
	}
}
