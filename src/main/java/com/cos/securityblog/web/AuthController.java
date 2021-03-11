package com.cos.securityblog.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.cos.securityblog.service.AuthService;
import com.cos.securityblog.web.auth.dto.AuthJoinReqDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class AuthController {
	
	private final AuthService authService;
	
	// 주소 /user , /post , /loginForm (인증이 안되어있는 경우)
		@GetMapping("/loginForm")
		public String loginForm() {
			return "auth/loginForm";
		}
		
		@GetMapping("/joinForm")
		public String joinForm() {
			return "auth/joinForm";
		}
		
		@PostMapping("/join")
		public String join(AuthJoinReqDto authJoinReqDto){
			authService.회원가입(authJoinReqDto.toEntity());
			return "redirect:loginForm";
		}
		
		@GetMapping("/login")
		public String login() {
			return "login 성공";
		}
}
