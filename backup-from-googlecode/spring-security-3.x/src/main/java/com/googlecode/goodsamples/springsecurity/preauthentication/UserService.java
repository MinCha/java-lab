package com.googlecode.goodsamples.springsecurity.preauthentication;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.AuthenticationUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class UserService implements AuthenticationUserDetailsService<Authentication> {
	@Override
	public UserDetails loadUserDetails(Authentication authentication) throws UsernameNotFoundException {
		PreAuthenticatedPrincipal principal = (PreAuthenticatedPrincipal)authentication.getPrincipal();
		
		if (principal.isAuthenticated() == false) {
			throw new UsernameNotFoundException("Failed Authentication");
		}
		
		return new User(principal.id());
	}
}
