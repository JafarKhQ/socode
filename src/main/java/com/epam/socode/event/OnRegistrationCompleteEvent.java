package com.epam.socode.event;

import com.epam.socode.domain.Profile;
import com.epam.socode.domain.VerificationKey;
import org.springframework.context.ApplicationEvent;

/**
 * @author Krystian_Balwierz
 */
public class OnRegistrationCompleteEvent extends ApplicationEvent {
    private static final long serialVersionUID = 6963784933841027704L;

    private VerificationKey verificationKey;

    public OnRegistrationCompleteEvent(Profile profile, VerificationKey verificationKey) {
        super(profile);
        this.verificationKey = verificationKey;
    }

    @Override
    public Profile getSource() {
        return (Profile) super.getSource();
    }

    public VerificationKey getVerificationKey() {
        return verificationKey;
    }
}
