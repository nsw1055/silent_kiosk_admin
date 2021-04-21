package org.judy.common.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import lombok.extern.log4j.Log4j;

@Log4j
public class Filtertest extends UsernamePasswordAuthenticationFilter {

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		// TODO Auto-generated method stub

		return super.attemptAuthentication(request, response);
	}


	@Override
	protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException failed) throws IOException, ServletException {
		// TODO Auto-generated method stub
		setAuthenticationFailureHandler(new SimpleUrlAuthenticationFailureHandler("/sample/customLogin"));
		super.unsuccessfulAuthentication(request, response, failed);
	}


	@Override
	protected String obtainPassword(HttpServletRequest request) {
		
		return super.obtainPassword(request);
	}

	@Override
	protected String obtainUsername(HttpServletRequest request) {

		return super.obtainUsername(request);
	}

	@Override
	protected void setDetails(HttpServletRequest request, UsernamePasswordAuthenticationToken authRequest) {
		String auth = request.getParameter("auth");


		request.getSession().setAttribute("auth", auth);
		super.setDetails(request, authRequest);
	}

	@Override
	public void setUsernameParameter(String usernameParameter) {
		// TODO Auto-generated method stub
		super.setUsernameParameter(usernameParameter);
	}

	@Override
	public void setPasswordParameter(String passwordParameter) {
		// TODO Auto-generated method stub
		super.setPasswordParameter(passwordParameter);
	}

	@Override
	public void setPostOnly(boolean postOnly) {
		// TODO Auto-generated method stub
		super.setPostOnly(postOnly);
	}

}
