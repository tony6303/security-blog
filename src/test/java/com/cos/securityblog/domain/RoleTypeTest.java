package com.cos.securityblog.domain;

import org.junit.jupiter.api.Test;

import com.cos.securityblog.domain.user.RoleType;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RoleTypeTest {

	@Test
	public void role_값검증() {
		log.debug("1: " +RoleType.ADMIN);
	}
}
