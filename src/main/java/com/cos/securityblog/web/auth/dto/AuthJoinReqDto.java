package com.cos.securityblog.web.auth.dto;

import com.cos.securityblog.domain.user.User;

import lombok.Data;

// Valid 처리

@Data
public class AuthJoinReqDto {
	private String username;
	private String password;
	private String email;

	public User toEntity() {
		return User.builder()
			.username(username)
			.password(password)
			.email(email)
			.build();
	}
}
