package com.epam.socode.listener;

import com.epam.socode.domain.Profile;
import com.epam.socode.domain.VerificationKey;
import com.epam.socode.event.OnRegistrationCompleteEvent;
import com.epam.socode.service.ProfileVerificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.MessageSource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import java.util.Locale;

/**
 * @author Krystian_Balwierz
 */
@Component
public class RegistrationListener implements ApplicationListener<OnRegistrationCompleteEvent> {

    @Autowired
    private JavaMailSender mailSender;
    @Autowired
    private MessageSource messageSource;
    @Autowired
    private ProfileVerificationService profileVerificationService;

    @Override
    public void onApplicationEvent(OnRegistrationCompleteEvent event) {
        sendConfirmationEmail(event.getSource());
    }

    private void sendConfirmationEmail(Profile profile) {
        VerificationKey verificationKey = profileVerificationService.createVerificationKey(profile);

        SimpleMailMessage email = new SimpleMailMessage();
        email.setTo(profile.getEmail());
        email.setSubject("Confirm your registration");
        email.setText(String.format(Locale.ENGLISH,
                "Your verification key is %s",
                verificationKey.getKey()));

        mailSender.send(email);
    }
}
