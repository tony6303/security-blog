package com.cos.securityblog.web.user.dto;

import lombok.Data;

@Data
public class UserUpdateReqDto {
	private String username;
	private String password;
	private String email;
	
	// toEntity 안만드는 이유 = 더티체킹 할것
}
