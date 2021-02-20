package com.example.security.services;

import org.springframework.mail.SimpleMailMessage;

public interface EmailService {
	void sendEmail(SimpleMailMessage emailMsg);
}