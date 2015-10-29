package com.epam.socode.service;

import com.epam.socode.annotation.ControllerTest;
import com.epam.socode.domain.Profile;
import com.epam.socode.request.Login;
import com.epam.socode.request.ProfileUpdate;
import com.epam.socode.request.Signup;
import org.apache.logging.log4j.util.Strings;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * @author jafar_qaddoumi
 */
@ControllerTest
@RunWith(SpringJUnit4ClassRunner.class)
public class ProfileServiceTest {

    @Autowired
    private ProfileService profileService;
    @Autowired
    private AuthenticationService authenticationService;

    @Test
    public void testAddProfileFromSignup() throws Exception {
        String email = "testAddProfileFromSignup@email.com";
        Profile profile = addProfile(email);

        assertEquals(email, profile.getEmail());
        assertTrue(Strings.isNotEmpty(profile.getProfileId()));
    }

    @Test
    public void testUpdateProfileFromProfile() throws Exception {
        String name = "name";
        String surename = "surename";
        String mainLanguage = "Java";
        String email = "testUpdateProfileFromProfile@email.com";

        Profile profile = addProfile(email);
        String oldProfileId = profile.getProfileId();

        profile.setName(name);
        profile.setSurname(surename);
        profile.setMainLanguage(mainLanguage);

        profile = profileService.updateProfile(profile);

        assertEquals(name, profile.getName());
        assertEquals(surename, profile.getSurname());
        assertEquals(mainLanguage, profile.getMainLanguage());
        assertEquals(email, profile.getEmail());
        assertEquals(oldProfileId, profile.getProfileId());
    }

    @Test
    public void testUpdateProfileFromProfileUpdate() throws Exception {
        String name = "name";
        String surename = "surename";
        String mainLanguage = "Java";
        String email = "testUpdateProfileFromProfileUpdate@email.com";

        Profile profile = addProfile(email);
        String oldProfileId = profile.getProfileId();

        Login login = new Login();
        login.setLogin(email);
        login.setPassword(email);
        String token = authenticationService.login(login);

        ProfileUpdate update = new ProfileUpdate();
        update.setToken(token);
        update.setName(name);
        update.setSurname(surename);
        update.setProfileId(oldProfileId);
        update.setMainLanguage(mainLanguage);

        profile = profileService.updateProfile(update);

        assertEquals(name, profile.getName());
        assertEquals(surename, profile.getSurname());
        assertEquals(mainLanguage, profile.getMainLanguage());
        assertEquals(email, profile.getEmail());
        assertEquals(oldProfileId, profile.getProfileId());
    }

    @Test
    public void testEnableProfile() throws Exception {
        String email = "testEnableProfile@email.com";

        Profile profile = addProfile(email);
        String oldProfileId = profile.getProfileId();

        assertFalse(profile.isEnabled());

        profile = profileService.enableProfile(profile);

        assertEquals(oldProfileId, profile.getProfileId());
        assertTrue(profile.isEnabled());
    }

    @Test
    public void testFindProfileByEmail() throws Exception {
        String email = "testFindProfileByEmail@email.com";

        Profile profile = addProfile(email);
        Profile profileFind = profileService.findProfileByEmail(email);

        assertEquals(profile.getProfileId(), profileFind.getProfileId());
        assertEquals(profile.getEmail(), profileFind.getEmail());
    }

    @Test
    public void testFindProfileById() throws Exception {
        String email = "testFindProfileById@email.com";

        Profile profile = addProfile(email);
        Profile profileFind = profileService.findProfileById(profile.getProfileId());

        assertEquals(profile.getProfileId(), profileFind.getProfileId());
        assertEquals(profile.getEmail(), profileFind.getEmail());
    }

    private Profile addProfile(String email) {
        Signup signup = new Signup();
        signup.setLogin(email);
        signup.setPassword(email);

        return profileService.addProfileFromSignup(signup);
    }
}