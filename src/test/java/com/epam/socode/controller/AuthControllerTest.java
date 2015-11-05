package com.epam.socode.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import com.epam.socode.annotation.ControllerTest;
import com.epam.socode.domain.GroupData;
import com.epam.socode.domain.Profile;
import com.epam.socode.domain.VerificationKey;
import com.epam.socode.domain.WorkGroup;
import com.epam.socode.request.Signup;
import com.epam.socode.request.Verify;
import com.epam.socode.response.ErrorCodes;
import com.epam.socode.response.Response;
import com.epam.socode.service.AuthenticationService;
import com.epam.socode.service.GroupService;
import com.epam.socode.service.ProfileVerificationService;

/**
 * @author jafar_qaddoumi
 */
@ControllerTest
@RunWith(SpringJUnit4ClassRunner.class)
public class AuthControllerTest extends BaseControllerTest {

    @InjectMocks
    AuthController authController;
   

    @Autowired
    GroupService groupService;

    @Autowired
    ProfileVerificationService profileVerificationService;

    @Autowired
    AuthenticationService authenticationService;

    @Test
    public void singUpWithoutGroupTest() throws Exception {
        String email = "singUpWithoutGroup@email.com";

        Response response = getResponseFromResult(signUpProfile(email, null));

        assertEquals(Response.STATUS_SUCCESS, response.getStatus());
        assertEquals(Response.CODE_SUCCESS, response.getStatusCode());
        assertNotNull(response.getData());

        Profile profile = getProfileFromResponse(response);
        assertEquals(email, profile.getEmail());
        assertTrue(profile.getParticipatedGroups().size() == 0);
    }

    @Test
    public void singUpTheSameProfileTest() throws Exception {
        String email = "singUpTheSameProfile1@email.com";

        getResponseFromResult(signUpProfile(email, null));
        Response response = getResponseFromResult(signUpProfile(email, null));

        assertEquals(Response.STATUS_FAIL, response.getStatus());
        assertEquals(ErrorCodes.PROFILE_EXIST_ERROR, response.getStatusCode());
    }

    @Test
    public void singUpWithGroupTest() throws Exception {
        String email = "singUpWithGroup@email.com";
        WorkGroup workGroup = groupService.addGroup(new GroupData("singUpWithGroupTest",""));

        Response response = getResponseFromResult(signUpProfile(email, workGroup));
        assertEquals(Response.STATUS_SUCCESS, response.getStatus());
        assertEquals(Response.CODE_SUCCESS, response.getStatusCode());
        assertNotNull(response.getData());

        Profile profile = getProfileFromResponse(response);
        assertEquals(email, profile.getEmail());
        assertTrue(profile.getParticipatedGroups().size() == 1);
        if (profile.getParticipatedGroups().size() > 0)
            assertEquals(workGroup.getGroupId(), profile.getParticipatedGroups().get(0).getGroupId());
    }

    @Test
    public void verifyPositiveTest() throws Exception {
        String email = "verifyPositive@email.com";

        Response response = getResponseFromResult(signUpProfile(email, null));
        Profile profile = getProfileFromResponse(response);

        response = getResponseFromResult(verifyProfile(profile));
        assertEquals(Response.STATUS_SUCCESS, response.getStatus());
        assertEquals(Response.CODE_SUCCESS, response.getStatusCode());

        profile = getProfileFromResponse(response);
        assertTrue(profile.isEnabled());
    }

    @Test
    public void verifyWrongVerificationKeyNegativeTest() throws Exception {
        String email = "verifyWrongVerificationKeyNegative@email.com";

        signUpProfile(email, null);

        Verify verify = new Verify();
        verify.setEmail(email);
        verify.setVerifyKey("dummy_key123456789");

        MvcResult verficationResult = mockMvc.perform(
                MockMvcRequestBuilders.post(AuthController.MAPPING_API_AUTH + AuthController.MAPPING_VERIFY)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(verify))
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print()).andReturn();

        Response response = getResponseFromResult(verficationResult);
        assertEquals(Response.STATUS_FAIL, response.getStatus());
        assertEquals(ErrorCodes.EMAIL_VERIFICATION_ERROR, response.getStatusCode());
    }

    @Test
    public void verifyWrongEmailNegativeTest() throws Exception {
        Signup signup = new Signup();
        String email = "verifyWrongEmailNegative@email.com";
        signup.setLogin(email);
        signup.setPassword(password);

        Response response = getResponseFromResult(signUpProfile(email, null));
        Profile profile = getProfileFromResponse(response);
        profile.setEmail("dummy@email.com");

        response = getResponseFromResult(verifyProfile(profile));
        assertEquals(Response.STATUS_FAIL, response.getStatus());
        assertEquals(ErrorCodes.EMAIL_VERIFICATION_ERROR, response.getStatusCode());
    }

    @Test
    public void loginPositiveTest() throws Exception {
        String email = "loginPositive@email.com";
        Response response = getResponseFromResult(signUpProfile(email, null));
        Profile profile = getProfileFromResponse(response);

        response = getResponseFromResult(verifyProfile(profile));
        profile = getProfileFromResponse(response);
        assertTrue(profile.isEnabled());

        response = getResponseFromResult(loginProfile(profile));
        assertEquals(Response.STATUS_SUCCESS, response.getStatus());
        assertEquals(Response.CODE_SUCCESS, response.getStatusCode());

        assertNotNull(response.getData());
    }

    @Test
    public void loginWrongEmailNegativeTest() throws Exception {
        String email = "loginWrongEmailNegative@email.com";

        Response response = getResponseFromResult(signUpProfile(email, null));
        Profile profile = getProfileFromResponse(response);

        response = getResponseFromResult(verifyProfile(profile));
        profile = getProfileFromResponse(response);
        assertTrue(profile.isEnabled());

        profile.setEmail("dummy@email.com");
        response = getResponseFromResult(loginProfile(profile));
        assertEquals(Response.STATUS_FAIL, response.getStatus());
        assertEquals(ErrorCodes.WRONG_EMAIL_PASSWORD_ERROR, response.getStatusCode());
    }

    @Test
    public void loginWrongPasswordNegativeTest() throws Exception {
        String email = "loginWrongPasswordNegative@email.com";

        Response response = getResponseFromResult(signUpProfile(email, null));
        Profile profile = getProfileFromResponse(response);

        response = getResponseFromResult(verifyProfile(profile));
        profile = getProfileFromResponse(response);
        assertTrue(profile.isEnabled());

        response = getResponseFromResult(loginProfile(profile, "wrongPassword"));
        assertEquals(Response.STATUS_FAIL, response.getStatus());
        assertEquals(ErrorCodes.WRONG_EMAIL_PASSWORD_ERROR, response.getStatusCode());
    }

    @Test
    public void logoutPositiveTest() throws Exception {
        String email = "logoutPositive@email.com";

        Response response = getResponseFromResult(signUpProfile(email, null));
        Profile profile = getProfileFromResponse(response);

        response = getResponseFromResult(verifyProfile(profile));
        profile = getProfileFromResponse(response);
        assertTrue(profile.isEnabled());

        response = getResponseFromResult(loginProfile(profile));
        assertEquals(Response.STATUS_SUCCESS, response.getStatus());
        assertEquals(Response.CODE_SUCCESS, response.getStatusCode());

        String token = getStringFromResponse(response);

        response = getResponseFromResult(logoutProfile(token));
        assertEquals(Response.STATUS_SUCCESS, response.getStatus());
        assertEquals(Response.CODE_SUCCESS, response.getStatusCode());
    }

    @Test
    public void logoutWrongTokenNegativeTest() throws Exception {
        String email = "logoutWrongTokenNegative@email.com";

        Response response = getResponseFromResult(signUpProfile(email, null));
        Profile profile = getProfileFromResponse(response);

        response = getResponseFromResult(verifyProfile(profile));
        profile = getProfileFromResponse(response);
        assertTrue(profile.isEnabled());

        response = getResponseFromResult(loginProfile(profile));
        assertEquals(Response.STATUS_SUCCESS, response.getStatus());
        assertEquals(Response.CODE_SUCCESS, response.getStatusCode());

        response = getResponseFromResult(logoutProfile("dummyToken"));
        assertEquals(Response.STATUS_FAIL, response.getStatus());
        assertEquals(ErrorCodes.INVALID_TOKEN_ERROR, response.getStatusCode());
    }

    @Test
    public void logoutTokenExpiredNegativeTest() throws Exception {
        String email = "logoutTokenExpiredNegative@email.com";

        Response response = getResponseFromResult(signUpProfile(email, null));
        Profile profile = getProfileFromResponse(response);

        response = getResponseFromResult(verifyProfile(profile));
        profile = getProfileFromResponse(response);
        assertTrue(profile.isEnabled());

        response = getResponseFromResult(loginProfile(profile));
        assertEquals(Response.STATUS_SUCCESS, response.getStatus());
        assertEquals(Response.CODE_SUCCESS, response.getStatusCode());

        String token = getStringFromResponse(response);
        authenticationService.setExpirationTimeMin(0);
        response = getResponseFromResult(logoutProfile(token));
        assertEquals(Response.STATUS_FAIL, response.getStatus());
        assertEquals(ErrorCodes.EXPIRED_TOKEN_ERROR, response.getStatusCode());
    }

    private MvcResult verifyProfile(Profile profile) throws Exception {
        String profileId = profile.getProfileId();
        VerificationKey verificationKey = profileVerificationService.findVerificationTokenByProfileId(profileId);
        assertNotNull(verificationKey);

        Verify verify = new Verify();
        verify.setEmail(profile.getEmail());
        verify.setVerifyKey(verificationKey.getKey());

        return mockMvc.perform(
                MockMvcRequestBuilders.post(AuthController.MAPPING_API_AUTH + AuthController.MAPPING_VERIFY)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(verify))
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print()).andReturn();
    }
}
