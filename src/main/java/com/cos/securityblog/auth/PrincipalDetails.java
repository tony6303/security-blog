package com.cos.securityblog.auth;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.cos.securityblog.domain.user.User;

public class PrincipalDetails implements UserDetails{
	
	private User user;
	
	
	// 내가 만듬
	public User getUser() {
		return user;
	}

	public PrincipalDetails(User user) {
		this.user = user;
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
