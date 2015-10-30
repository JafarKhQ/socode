package com.epam.socode.listener;

import com.epam.socode.domain.Profile;
import com.epam.socode.domain.VerificationKey;
import com.epam.socode.event.OnRegistrationCompleteEvent;
import com.epam.socode.service.ProfileVerificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.MessageSource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

/**
 * @author Krystian_Balwierz
 */
@Component
public class RegistrationListener implements ApplicationListener<OnRegistrationCompleteEvent> {

    @Autowired
    private ProfileVerificationService profileVerificationService;

    @Autowired
    private MessageSource messageSource;

    @Autowired
    private JavaMailSender mailSender;

    @Override
    public void onApplicationEvent(OnRegistrationCompleteEvent event) {
        this.confirmRegistration(event);
    }

    private void confirmRegistration(OnRegistrationCompleteEvent event) {
        Profile profile = event.getSource();
        VerificationKey verificationKey = profileVerificationService.createVerificationKey(profile);

        String recipientAddress = profile.getEmail();
        String subject = "Registration Confirmation";
        String confirmationUrl = "/regitrationConfirm.html?token=" + verificationKey.getKey();
        String message = "Succesful! "; // TODO:
                                        // messageSource.getMessage("message.regSucc",
                                        // null, null);

        // SimpleMailMessage email = new SimpleMailMessage();
        // email.setTo(recipientAddress);
        // email.setSubject(subject);
        // email.setText(message + " rn" + "http://localhost:8080/" +
        // confirmationUrl);
        // mailSender.send(email);
    }
}
