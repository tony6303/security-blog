package com.cos.securityblog.config;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Bean // ioc등록만하면 알아서해줌
	public BCryptPasswordEncoder encode() {
		return new BCryptPasswordEncoder();
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable(); // 안하면 form 태그 사용 불가능
		http.authorizeRequests()
			.antMatchers("/user/**","/post/**").access("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')") //.authenticated() // 인증이 필요한 페이지들
			.antMatchers("/admin/**").access("hasRole('ROLE_ADMIN')") // ROLE_ << 강제성있음
			.anyRequest().permitAll()
		.and()
			.formLogin()
			.loginPage("/loginForm") // x-www-url-encoded
		// 어렵다 !!!
		.loginProcessingUrl("/login")  // /login 주소 요청이 들어오면 시큐리티가 낚아챈다.
		.defaultSuccessUrl("/"); // 로그인이 성공하면 어디로 보낼지. 
		// 원래 가려던 페이지가 인증에 막혔을때, 인증을 성공하면 원래 가려던 곳으로 보내게 해줌
		
//		.successHandler(new AuthenticationSuccessHandler() {
//			
//			@Override
//			public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
//					Authentication authentication) throws IOException, ServletException {
//				response.sendRedirect("/"); 
//				
//			}
//		});
			
	}

}
