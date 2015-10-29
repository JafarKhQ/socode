package com.epam.socode.controller;

import com.epam.socode.annotation.ControllerTest;
import com.epam.socode.domain.Profile;
import com.epam.socode.request.ProfileData;
import com.epam.socode.request.ProfileUpdate;
import com.epam.socode.response.Response;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import static org.junit.Assert.assertEquals;

/**
 * @author jafar_qaddoumi
 */
@ControllerTest
@RunWith(SpringJUnit4ClassRunner.class)
public class ProfileControllerTest extends BaseControllerTest {

    @InjectMocks
    ProfileController profileController;

    @Test
    public void testProfileData() throws Exception {
        String email = "testProfileData@email.com";

        Profile profile = getProfileFromResponse(
                getResponseFromResult(
                        signUpProfile(email, null)
                )
        );

        String token = getStringFromResponse(
                getResponseFromResult(
                        loginProfile(profile)
                )
        );

        ProfileData profileData = new ProfileData();
        profileData.setProfileId(profile.getProfileId());
        profileData.setToken(token);

        MvcResult result = mockMvc.perform(
                MockMvcRequestBuilders.post(ProfileController.MAPPING_API_AUTH_PROFILE)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(profileData))
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print()).andReturn();

        Response response = getResponseFromResult(result);
        assertEquals(Response.STATUS_SUCCESS, response.getStatus());
        assertEquals(Response.CODE_SUCCESS, response.getStatusCode());

        Profile fullProfile = getProfileFromResponse(response);

        assertEquals(profile.getName(), fullProfile.getName());
        assertEquals(profile.getEmail(), fullProfile.getEmail());
        assertEquals(profile.getJoinDate(), fullProfile.getJoinDate());
        assertEquals(profile.getSurname(), fullProfile.getSurname());
        assertEquals(profile.getProfileId(), fullProfile.getProfileId());
    }

    @Test
    public void testProfileUpdate() throws Exception {
        String email = "testProfileUpdate@email.com";
        String name = "Name";
        String surename = "TwoName";
        String mainLanguages = "Java, C++, C";

        // signUp
        Profile profile = getProfileFromResponse(
                getResponseFromResult(
                        signUpProfile(email, null)
                )
        );

        // login
        String token = getStringFromResponse(
                getResponseFromResult(
                        loginProfile(profile)
                )
        );

        ProfileUpdate profileUpdate = new ProfileUpdate();
        profileUpdate.setProfileId(profile.getProfileId());
        profileUpdate.setToken(token);
        profileUpdate.setName(name);
        profileUpdate.setMainLanguage(mainLanguages);
        profileUpdate.setSurname(surename);

        // update profile
        MvcResult result = mockMvc.perform(
                MockMvcRequestBuilders.post(ProfileController.MAPPING_API_AUTH_PROFILE + BaseController.MAPPING_UPDATE)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(profileUpdate))
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print()).andReturn();

        Response response = getResponseFromResult(result);
        assertEquals(Response.STATUS_SUCCESS, response.getStatus());
        assertEquals(Response.CODE_SUCCESS, response.getStatusCode());

        Profile fullProfile = getProfileFromResponse(response);

        assertEquals(fullProfile.getName(), name);
        assertEquals(fullProfile.getEmail(), email);
        assertEquals(fullProfile.getSurname(), surename);
        assertEquals(fullProfile.getMainLanguage(), mainLanguages);

        ProfileData profileData = new ProfileData();
        profileData.setProfileId(profile.getProfileId());
        profileData.setToken(token);

        // get Profile
        result = mockMvc.perform(
                MockMvcRequestBuilders.post(ProfileController.MAPPING_API_AUTH_PROFILE)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(profileData))
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print()).andReturn();

        response = getResponseFromResult(result);
        assertEquals(Response.STATUS_SUCCESS, response.getStatus());
        assertEquals(Response.CODE_SUCCESS, response.getStatusCode());

        fullProfile = getProfileFromResponse(response);

        assertEquals(fullProfile.getName(), name);
        assertEquals(fullProfile.getEmail(), email);
        assertEquals(fullProfile.getSurname(), surename);
        assertEquals(fullProfile.getMainLanguage(), mainLanguages);
    }
}