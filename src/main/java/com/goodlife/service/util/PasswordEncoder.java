package com.goodlife.service.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service("passwdEncoderService")
public class PasswordEncoder {

	public String encodePassword(String passwd) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String hashedPassword = passwordEncoder.encode(passwd);
		return hashedPassword;
	}
}
