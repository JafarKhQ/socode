package com.epam.socode.listener;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.MessageSource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import com.epam.socode.domain.Profile;
import com.epam.socode.event.OnRegistrationCompleteEvent;
import com.epam.socode.service.ProfileService;

@Component
public class RegistrationListener implements ApplicationListener<OnRegistrationCompleteEvent> {
	@Autowired
	private ProfileService profileService;
	@Autowired
	private MessageSource messageSource;
	@Autowired
	private JavaMailSender mailSender;

	@Override
	public void onApplicationEvent(OnRegistrationCompleteEvent event) {
		this.confirmRegistration(event);
	}

	private void confirmRegistration(OnRegistrationCompleteEvent event) {
		Profile profile = event.getProfile();
		String token = UUID.randomUUID().toString();
		profileService.createVerificationToken(profile, token);

		String recipientAddress = profile.getEmail();
		String subject = "Registration Confirmation";
		String confirmationUrl = "/regitrationConfirm.html?token=" + token;
		String message = "Succesful! "; // TODO:
										// messageSource.getMessage("message.regSucc",
										// null, null);
		SimpleMailMessage email = new SimpleMailMessage();
		email.setTo(recipientAddress);
		email.setSubject(subject);
		email.setText(message + " rn" + "http://localhost:8080/" + confirmationUrl);
		mailSender.send(email);
	}
}
