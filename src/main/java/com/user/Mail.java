package com.user;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Mail {
	
	private String from;

	private String to;

	private String subject;

	private String message;
	
	private boolean isHtml;

	public Mail() {
		
	}
	public Mail(String from, String to, String subject, String message) {
		this.from = from;
		this.subject = subject;
		this.message = message;
		this.to = to;
	}
	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
	}
	public String getTo() {
		return to;
	}
	public void setTo(String to) {
		this.to = to;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public boolean isHtml() {
		return isHtml;
	}
	public void setHtml(boolean isHtml) {
		this.isHtml = isHtml;
	}
}