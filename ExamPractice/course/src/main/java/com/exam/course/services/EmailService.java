package com.exam.course.services;

import org.springframework.mail.SimpleMailMessage;

public interface EmailService {
	void sendEmail(SimpleMailMessage emailMsg);
}