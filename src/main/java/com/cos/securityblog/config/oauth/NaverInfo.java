package com.cos.securityblog.config.oauth;

import java.util.Map;

public class NaverInfo extends OAuth2UserInfo{
	
	public NaverInfo(Map<String, Object> attributes) {
		super(attributes);
	}

	@Override
	public String getId() {
		return (String)attributes.get("id"); 
	}

	@Override
	public String getName() {
		return (String)attributes.get("name");
	}

	@Override
	public String getEmail() {
		return (String)attributes.get("email");
	}

	@Override
	public String getImageUrl() {
		return null;
	}

	// 완전한 Primary Key 가 된다 !!
	@Override
	public String getUsername() {
		return "Naver_"+(String)attributes.get("id");
	}
	
	

	
}
