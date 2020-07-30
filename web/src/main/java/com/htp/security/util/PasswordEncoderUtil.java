package com.htp.security.util;

import org.springframework.security.crypto.password.PasswordEncoder;

public class PasswordEncoderUtil implements org.springframework.security.crypto.password.PasswordEncoder {
	private static final PasswordEncoder INSTANCE = new PasswordEncoderUtil();

	/* Ничего кодировать не будем. А то отлаживаться неудобно ;) */
	public String encode(CharSequence rawPassword) {
		return rawPassword.toString();
	}

	public boolean matches(CharSequence rawPassword, String encodedPassword) {
		return rawPassword.toString().equals(encodedPassword);
	}

	public static PasswordEncoderUtil getInstance() {
		return (PasswordEncoderUtil) INSTANCE;
	}

}

