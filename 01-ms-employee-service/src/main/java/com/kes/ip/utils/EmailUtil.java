package com.kes.ip.utils;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import jakarta.mail.internet.MimeMessage;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@AllArgsConstructor
public class EmailUtil {

	private JavaMailSender javaMailSender;

	public boolean sendEmail(String toAddr, String subject, String body) {

		boolean result = false;

		MimeMessage mimeMessage = null;

		MimeMessageHelper messageHelper = null;

		try {
			mimeMessage = javaMailSender.createMimeMessage();

			messageHelper = new MimeMessageHelper(mimeMessage, true);

			messageHelper.setTo(toAddr);
			messageHelper.setSubject(subject);
			messageHelper.setText(body);

			javaMailSender.send(mimeMessage);

			if (!result) {
				log.info("Email Sent Successfully.!");
			} else {
				log.info("Unable to Send Email..??");
			}

		} catch (Exception e) {
			log.error("Exception Occured While Sending Email");
			e.printStackTrace();
		}

		return result;
	}
}
