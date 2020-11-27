package com.almuhannd.security;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.stereotype.Service;
@Service
public class SecurityConfig  extends WebSecurityConfigurerAdapter{
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.authorizeRequests()
//				.antMatchers("/admin/**").hasAnyRole("ADMIN")
				.antMatchers("/").permitAll()
				.antMatchers("/admin/**").hasAnyRole("admin")
					.and()
						.formLogin()
							.loginPage("/login")
					.and()
						.logout()
							.logoutSuccessUrl("/")
					.and()
						.exceptionHandling()
							.accessDeniedPage("/");
	}

}
