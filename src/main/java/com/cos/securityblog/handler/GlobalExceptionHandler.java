package com.cos.securityblog.handler;

import java.sql.SQLIntegrityConstraintViolationException;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import com.cos.securityblog.dto.CommonRespDto;

@ControllerAdvice
@RestController
public class GlobalExceptionHandler {
	@ExceptionHandler(value=SQLIntegrityConstraintViolationException.class)
	public CommonRespDto<?> SQLIntegrityConstraintViolationException(Exception e) {
		System.out.println(e.getMessage());
		return new CommonRespDto<>(-1, "username이 중복되었습니다."); // 500
		
	}
}
