package com.cos.securityblog.config.oauth;

import java.util.Map;

public abstract class OAuth2UserInfo {
	protected Map<String, Object> attributes;

	public OAuth2UserInfo(Map<String, Object> attributes) {
		this.attributes = attributes;
	}
	
	public Map<String, Object> getAttributes(){
		return attributes;
	}
	
	
	// 어디서 로그인을 하든, 이 네가지만 있으면 될것이다! 라고 개발자가 판단
	public abstract String getUsername();
	public abstract String getId();
	public abstract String getName();
	public abstract String getEmail();
	public abstract String getImageUrl();
}
