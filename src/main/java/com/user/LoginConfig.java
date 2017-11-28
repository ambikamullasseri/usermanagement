package com.user;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoginConfig {

	Map<String,LocalDateTime> loginLinkExpiryMap;
	Map<String,UserInfo> userMap;
	
	@Bean
	public Map<String,LocalDateTime> getExpiryMap() {
		loginLinkExpiryMap = new ConcurrentHashMap<>();
		return loginLinkExpiryMap;
	}

	@Bean
	public Map<String,UserInfo> getUserDetails() {
		userMap = new ConcurrentHashMap<>();
		return userMap;
	}
}
