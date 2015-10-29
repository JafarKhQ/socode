package com.epam.socode.event;

import com.epam.socode.domain.Profile;
import org.springframework.context.ApplicationEvent;

/**
 * @author Krystian_Balwierz
 */
public class OnRegistrationCompleteEvent extends ApplicationEvent {
    private static final long serialVersionUID = 6963784933841027704L;

    public OnRegistrationCompleteEvent(Profile profile) {
        super(profile);
    }

    @Override
    public Profile getSource() {
        return (Profile) super.getSource();
    }
}
