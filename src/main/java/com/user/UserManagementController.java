package com.user;

import java.time.LocalDateTime;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.resource.UserInfoRestTemplateCustomizer;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/usermanagement")
public class UserManagementController {

	@Autowired
	private JavaMailSender sender;

	@Autowired 
	private LoginConfig loginConfig;

	@RequestMapping(
			value="/user",
			method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON_VALUE)
	public String registerUser (@RequestBody UserInfo userInfo) {
		
		String userId =  ((Integer)(userInfo.getEmail()+userInfo.getUserName()).hashCode()).toString();
		loginConfig.getExpiryMap().put(userId, LocalDateTime.now());
		try {
			sendEmail(userInfo.getEmail());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return userId;	
	}

	@RequestMapping(
			value="/login/{userId}",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UserInfo> login (@PathVariable("userId") String userId) {
		LocalDateTime time = loginConfig.getExpiryMap().get(userId);
		if (null != time) {
			if (time.plusMinutes(15).isBefore(LocalDateTime.now())) {
				return  new ResponseEntity<>(null, HttpStatus.REQUEST_TIMEOUT);
			} else {
				return  new ResponseEntity<>(loginConfig.getUserDetails().get(userId), HttpStatus.OK);
			}
		} else {
			return  new ResponseEntity<>(null, HttpStatus.REQUEST_TIMEOUT);
		}
	}
	private void sendEmail(String email) throws Exception{

		MimeMessage message = sender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message);
		helper.setTo(email);
		helper.setText("<html><body>Click on " + "<a href\"link\">" +"to register" +"</body></html>",true);
		helper.setSubject("Hi");
		sender.send(message);
	}

}
