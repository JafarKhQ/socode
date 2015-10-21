package com.epam.socode.event;

import org.springframework.context.ApplicationEvent;

import com.epam.socode.domain.Profile;

/**
 * 
 * @author Krystian_Balwierz
 *
 */
public class OnRegistrationCompleteEvent extends ApplicationEvent {
    private static final long serialVersionUID = 6963784933841027704L;
    private final Profile profile;

    public OnRegistrationCompleteEvent(Profile profile) {
        super(profile);

        this.profile = profile;
    }

    public Profile getProfile() {
        return profile;
    }
}
