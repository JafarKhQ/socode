package com.epam.socode.listener;

import com.epam.socode.domain.Profile;
import com.epam.socode.domain.VerificationKey;
import com.epam.socode.event.OnRegistrationCompleteEvent;
import com.epam.socode.service.ProfileVerificationService;
import com.epam.socode.util.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
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
        Profile profile = event.getSource();
        VerificationKey verificationKey = profileVerificationService.createVerificationKey(profile);

        if (null != mailSender) {
            sendConfirmationEmail(profile.getEmail(), verificationKey.getKey());
        }
    }

    private void sendConfirmationEmail(String emailAddress, String key) {
        Locale locale = LocaleContextHolder.getLocale();
        String subject = messageSource.getMessage("email.confirm.subject",
                null,
                locale);
        String body = messageSource.getMessage("email.confirm.text",
                Utils.toArray(key),
                locale);

        SimpleMailMessage email = new SimpleMailMessage();
        email.setTo(emailAddress);
        email.setSubject(subject);
        email.setText(body);

        mailSender.send(email);
    }
}
