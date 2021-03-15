package com.cos.securityblog.config.oauth;

import java.util.Map;
import java.util.UUID;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import com.cos.securityblog.config.auth.PrincipalDetails;
import com.cos.securityblog.domain.user.RoleType;
import com.cos.securityblog.domain.user.User;
import com.cos.securityblog.domain.user.UserRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class OAuth2DetailsService extends DefaultOAuth2UserService{
	
	private final UserRepository userRepository;
	

	@Override
	public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
		System.out.println("OAuth 로그인 진행중.");
		
		System.out.println("AccessToken: " +userRequest.getAccessToken());
		// (출력)회원정보 요청 -> org.springframework.security.oauth2.core.OAuth2AccessToken@3a7aee03
		// <-응답 액세스 토큰으로 회원정보를 응답받음 . 
		
		// 카카오, 네이버로 하려면 GET요청을 모두 직접 만들어야 한다.
		// GET https://www.googleapis.com/drive/v2/files?access_token=access_token
		// GET 요청을 알아서 해줌.
		OAuth2User oauth2User = super.loadUser(userRequest);
		System.out.println("getAttribute: " +oauth2User.getAttributes()); // PrincipalDetails 가 OAuth2User 를 implements 중
		/**
		 * google
		 * {sub=103916751827347440723, 
		 * name=이대엽, given_name=이대엽, 
		 * picture=https://lh3.googleusercontent.com/-TaK4GMcWlms/AAAAAAAAAAI/AAAAAAAAAAA/AMZuuclXVUs08EATUOFIwhjH1ijTzzpVfw/s96-c/photo.jpg, 
		 * email=tony33535@gmail.com, 
		 * email_verified=true, 
		 * locale=ko}
		 * 
		 * facebook
		 * {id=103844888452662, name=이대엽, email=tony6303@naver.com}
		 */
		 System.out.println("isEmpty: "+ oauth2User.getAttributes().isEmpty());
		
		return processOAuth2User(userRequest, oauth2User);
	}

	// 구글 로그인 프로세스 , 오버라이딩 아님
	private OAuth2User processOAuth2User(OAuth2UserRequest userRequest, OAuth2User oauth2User) {
		System.out.println("뭐로 로그인했지 "+ userRequest.getClientRegistration().getClientName());
		// 1번 통합 클래스를 생성.    -> 무엇으로 로그인 했는지(깃허브, 구글, 페이스북) 통합시킴
		OAuth2UserInfo oAuth2UserInfo = null;
		if(userRequest.getClientRegistration().getClientName().equals("Google")) {
			oAuth2UserInfo = new GoogleInfo(oauth2User.getAttributes());
		} else if(userRequest.getClientRegistration().getClientName().equals("Facebook")) {
			oAuth2UserInfo = new FacebookInfo(oauth2User.getAttributes());
		} else if(userRequest.getClientRegistration().getClientName().equals("Naver")) {
			oAuth2UserInfo = new NaverInfo((Map<String, Object>)(oauth2User.getAttributes().get("response")));
		} else if(userRequest.getClientRegistration().getClientName().equals("Kakao")) {
			oAuth2UserInfo = new KakaoInfo((Map<String, Object>)(oauth2User.getAttributes().get("kakao_account")));
		}
		
		// 2번 최초로그인 -> 회원가입 + 로그인   /// 이미 회원  ->  로그인
		// userEntity에 sub(Google_sub), email 을 넣을예정 
		User userEntity = userRepository.findByUsername(oAuth2UserInfo.getUsername());
		UUID uuid = UUID.randomUUID();
		String encPassword = new BCryptPasswordEncoder().encode(uuid.toString());
		
		if(userEntity == null) { // 최초 로그인
			User user = User.builder()
					.username(oAuth2UserInfo.getUsername())
					.password(encPassword)
					.email(oAuth2UserInfo.getEmail())
					.role(RoleType.USER)
					.build();
			userEntity = userRepository.save(user);
			return new PrincipalDetails(userEntity, oauth2User.getAttributes()); // OAuth2User implement
		} else { // 이미 가입되어있음 -> 업데이트 까지 고려해야하지만 어려워서 패스
			return new PrincipalDetails(userEntity, oauth2User.getAttributes()); // OAuth2User implement
		}

	}
}
