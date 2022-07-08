package com.city.app.config;

import java.io.IOException;
import java.util.Objects;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.city.app.model.Role;
import com.city.app.model.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws ServletException, IOException {

		final String requestTokenHeader = request.getHeader("Authorization");

		String username = null;
		String jwtToken = null;
		// JWT Token is in the form "Bearer token". Remove Bearer word and get only the
		// Token
		if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {
			jwtToken = requestTokenHeader.substring(7);
			try {
				username = jwtTokenUtil.getUsernameFromToken(jwtToken);
			} catch (IllegalArgumentException e) {
				System.out.println("Unable to get JWT Token");
			} catch (ExpiredJwtException e) {
				System.out.println("JWT Token has expired");
			}
		} else {
			logger.warn("JWT Token does not begin with Bearer String");
		}

		// Once we get the token validate it.
		if (username != null && SecurityContextHolder.getContext().getAuthentication() == null
				&& isAuthozied(username)) {

			UserDetails userDetails = getUserDetails(jwtToken);
			// this.jwtUserDetailsService.loadUserByUsername(username);

			// if token is valid configure Spring Security to manually set authentication
			if (jwtTokenUtil.validateToken(jwtToken, userDetails)) {

				UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
						userDetails, null, userDetails.getAuthorities());
				usernamePasswordAuthenticationToken
						.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				// After setting the Authentication in the context, we specify
				// that the current user is authenticated. So it passes the Spring Security
				// Configurations successfully.
				SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
			}
		}
		chain.doFilter(request, response);
	}

	private UserDetails getUserDetails(String token) {
		User userDetails = new User();
		Claims claims = jwtTokenUtil.getAllClaimsFromToken(token);
		String subject = (String) claims.get(Claims.SUBJECT);
		String roles = (String) claims.get("roles");

		System.out.println("SUBJECT: " + subject);
		System.out.println("roles: " + roles);

		if (Objects.nonNull(roles)) {
			roles = roles.replace("[", "").replace("]", "");
			String[] roleNames = roles.split(",");

			for (String aRoleName : roleNames) {
				userDetails.addRole(new Role(aRoleName));
			}
		}
		userDetails.setName(subject);

		userDetails.setPassword("$2a$10$ZIp3hRM1haZqO0YGjOH6y.CZ7eyVgAshXdtugvbmYrWbVTFMKlfPq");

		return userDetails;
	}

	private boolean isAuthozied(String username) {
		if ("admin".equals(username)) {
			return true;
		} else {
			throw new UsernameNotFoundException("User not found with username: " + username);
		}
	}

}
