package com.epam.socode.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.apache.logging.log4j.util.Strings;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.epam.socode.annotation.ControllerTest;
import com.epam.socode.domain.Profile;

/**
 * @author jafar_qaddoumi
 */
@ControllerTest
@RunWith(SpringJUnit4ClassRunner.class)
public class ProfileRepositoryTest {

    @Autowired
    private ProfileRepository profileRepository;

    @Test
    public void testAddProfile() throws Exception {
        String email = "testAddProfile2@email.com";
        Profile profile = addProfile(email);

        assertEquals(email, profile.getEmail());
        assertTrue(Strings.isNotEmpty(profile.getProfileId()));
    }

    @Test
    public void testUpdateProfile() throws Exception {
        String name = "name";
        String surename = "surename";
        String mainLanguage = "Java";
        String email = "testUpdateProfile2@email.com";

        Profile profile = addProfile(email);
        String oldProfileId = profile.getProfileId();

        profile.setName(name);
        profile.setSurname(surename);
        profile.setMainLanguage(mainLanguage);

        profile = profileRepository.updateProfile(profile);

        assertEquals(name, profile.getName());
        assertEquals(surename, profile.getSurname());
        assertEquals(mainLanguage, profile.getMainLanguage());
        assertEquals(email, profile.getEmail());
        assertEquals(oldProfileId, profile.getProfileId());
    }

    @Test
    public void testFindProfileByEmail() throws Exception {
        String email = "testFindProfileByEmail2@email.com";

        Profile profile = addProfile(email);
        Profile profileFind = profileRepository.findProfileByEmail(email);

        assertEquals(profile.getProfileId(), profileFind.getProfileId());
        assertEquals(profile.getEmail(), profileFind.getEmail());
    }

    @Test
    public void testFindProfileById() throws Exception {
        String email = "testFindProfileById2@email.com";

        Profile profile = addProfile(email);
        Profile profileFind = profileRepository.findProfileById(profile.getProfileId());

        assertEquals(profile.getProfileId(), profileFind.getProfileId());
        assertEquals(profile.getEmail(), profileFind.getEmail());
    }

    private Profile addProfile(String email) {
        Profile profile = new Profile();
        profile.setEmail(email);
        profile.setPassword(email);

        return profileRepository.addProfile(profile);
    }
}