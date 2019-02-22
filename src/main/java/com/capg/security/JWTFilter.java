package com.capg.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class JWTFilter extends OncePerRequestFilter {

	@Autowired
	JWTService jwtService;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String header = request.getHeader("Authorization");

		if (header == null || !header.startsWith("Bearer ")) {
			filterChain.doFilter(request, response);
			return;
		}

		String jwt = header.replaceAll("Bearer ", "");
		logger.trace(jwt);

		try {
			UsernamePasswordAuthenticationToken auth = jwtService.getAuthenticationFromJWT(jwt);
			if (auth == null)
				throw new Exception("Token null...");
			SecurityContextHolder.getContext().setAuthentication(auth);

		} catch (Exception e) {

			String ex = e.toString();
			logger.trace(request.getRemoteAddr() + ex);
			((HttpServletResponse) response).setStatus(HttpServletResponse.SC_FORBIDDEN);
			((HttpServletResponse) response).getWriter().write(ex);
			return;
		}

		filterChain.doFilter(request, response);
	}

}
