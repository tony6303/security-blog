package com.cos.securityblog.web;

import java.security.Principal;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cos.securityblog.auth.PrincipalDetails;

@Controller
public class UserController {
	
	// 로그인 , 회원가입, 로그아운 (AuthController)
	
	// 사실 유저컨트롤러가 할게 많이없다... 유저정보 가져오기? 끝.

//	@GetMapping({"","/"})
//	public String home() {
//		return "index"; // ViewResolver 발동
//	}
	
	@GetMapping("/user")
	public @ResponseBody String hello() { // Controller + ResponseBody =>  RestController 와 같다
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		
		// 다양한 get을 할 수 있다
		//Principal details = (Principal) authentication.getDetails();
		
		System.out.println(authentication.getDetails());
		// WebAuthenticationDetails [RemoteIpAddress=0:0:0:0:0:0:0:1, SessionId=318F53CFFA9644558FDE4F56E0D1F6BD]
		
		PrincipalDetails details = (PrincipalDetails) authentication.getPrincipal();
		System.out.println(details);
		System.out.println(details.getUser());
		// User(id=11, username=2222, password=$2a$10$QfH4Xsm49gDA0T2IWyh9DeN5TouYGtx2GdghnSlm24NreYqPrcr5y, email=2222@naver.com, creaeteDate=2021-03-10 13:04:32.426, role=USER)
		
		
		return "Hello user";
	}
	
	
}
