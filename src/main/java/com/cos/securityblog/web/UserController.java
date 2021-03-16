package com.cos.securityblog.web;

import java.security.Principal;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cos.securityblog.config.auth.PrincipalDetails;
import com.cos.securityblog.domain.user.User;
import com.cos.securityblog.service.UserService;
import com.cos.securityblog.web.dto.CMRespDto;
import com.cos.securityblog.web.user.dto.UserUpdateReqDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class UserController {
	private final UserService userService;
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
	
	@GetMapping("/user/{id}")
	public String updateForm(@PathVariable int id, Model model) {
		model.addAttribute("id",id);
		return "user/updateForm";
	}
	
	@PutMapping("/user/{id}")
	public @ResponseBody CMRespDto<?> update(@PathVariable int id, @RequestBody UserUpdateReqDto userUpdateReqDto, @AuthenticationPrincipal PrincipalDetails principalDetails) {
		System.out.println("받은 데이터" + userUpdateReqDto);
		User userEntity = userService.회원수정(id, userUpdateReqDto);
		
		// 세션변경
		// UsernamePasswordToken -> Authentication 객체로 만들어서 시큐리티 컨텍스트 홀더에 집어넣으면 됨
//		Authentication authentication = 
//				new UsernamePasswordAuthenticationToken(userEntity.getUsername(), userEntity.getPassword());
//		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		
		principalDetails.setUser(userEntity);
		
		return new CMRespDto<>(1, null);
	}
	
	
}
