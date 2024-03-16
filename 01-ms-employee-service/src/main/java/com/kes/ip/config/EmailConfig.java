package com.kes.ip.config;

import java.util.Properties;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@Configuration
public class EmailConfig {

	@Bean
	public JavaMailSender javaMailSender() {

		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();

		mailSender.setPort(587);
		mailSender.setHost("smtp.gmail.com");
		mailSender.setUsername("swamymasna55@gmail.com");
		mailSender.setPassword("aqhvtgtcogekywfs");

		Properties properties = mailSender.getJavaMailProperties();
		properties.put("mail.smtp.auth", true);
		properties.put("mail.smtp.starttls.enable", true);

		return mailSender;
	}

}
