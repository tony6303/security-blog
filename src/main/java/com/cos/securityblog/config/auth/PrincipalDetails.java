package com.cos.securityblog.config.auth;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import com.cos.securityblog.domain.user.User;

import lombok.Data;

@Data
public class PrincipalDetails implements UserDetails, OAuth2User{
	
	private User user;
	private Map<String, Object> attributes; // OAuth 제공자로부터 받은 회원정보
	private boolean isOauth = false;

	// Constructor
	public PrincipalDetails(User user) {
		this.user = user;
	}
	
	public PrincipalDetails(User user, Map<String, Object> attributes) {
		this.user = user;
		this.attributes = attributes;
		this.isOauth = true;
	}
	
	// 왜 MAP으로 받죠 ? -> < 키 값, 오브젝트타입 > , (어디서 로그인했든 간에)모든 타입을 다 받을 수 있기때문에.
	// OAuth2User implements
	@Override
	public Map<String, Object> getAttributes() {
		return attributes;
	}

	@Override
	public String getName() {
		return "나중에~";
	}
	
	// 내가 만듬
	public User getUser() {
		return user;
	}

	@Override
	public String getPassword() {
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		return user.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() {
		// 너 만료됬니?
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		System.out.println("ROLE 검증 하는 중");
		Collection<GrantedAuthority> collectors = new ArrayList<>();
		// DB에 ROLE_USER 라고 저장하는건 별로 좋은방식이 아닌거같아요. 꺼내와서 쓸 때 붙여줍니다.
		collectors.add(()->{ return "ROLE_"+user.getRole();});
		return collectors;
	}
}
