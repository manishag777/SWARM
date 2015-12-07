package com.worksap.stm.SWARMS.utils;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

public class UserAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request,
			HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

		Collection<SimpleGrantedAuthority> authorities = (Collection<SimpleGrantedAuthority>)    SecurityContextHolder.getContext().getAuthentication().getAuthorities();
		
		for(SimpleGrantedAuthority authority : authorities){
			if(authority.getAuthority().equals("MD")){
				redirectStrategy.sendRedirect(request, response, "/admin");
			}
			else if(authority.getAuthority().equals("ss")){
				redirectStrategy.sendRedirect(request, response, "/searchProduct");
			}
			else if(authority.getAuthority().equals("cso")){
				redirectStrategy.sendRedirect(request, response, "/csodashboard");
			}
			else if(authority.getAuthority().equals("cashier")){
				redirectStrategy.sendRedirect(request, response, "/manageTransaction");
			}
			else if(authority.getAuthority().equals("sm")){
				redirectStrategy.sendRedirect(request, response, "/smDashboard");
			}
		}
		
	
	}
	
}
