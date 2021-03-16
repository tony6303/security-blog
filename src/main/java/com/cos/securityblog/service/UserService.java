package com.cos.securityblog.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cos.securityblog.domain.user.User;
import com.cos.securityblog.domain.user.UserRepository;
import com.cos.securityblog.web.user.dto.UserUpdateReqDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserService {
	private final UserRepository userRepository;
	private final BCryptPasswordEncoder encoder;
	
	@Transactional
	public User 회원수정(int id, UserUpdateReqDto userUpdateReqDto) {
		User userEntity = userRepository.findById(id).get(); // 1차 캐시
		String encPassword = encoder.encode(userUpdateReqDto.getPassword());
		
		userEntity.setPassword(encPassword);
		userEntity.setEmail(userUpdateReqDto.getEmail());
		return userEntity;
	} // 업데이트는 항상 더티체킹!! 
}
