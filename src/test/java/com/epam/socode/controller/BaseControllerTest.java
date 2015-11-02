package com.epam.socode.controller;

import com.epam.socode.domain.Profile;
import com.epam.socode.domain.WorkGroup;
import com.epam.socode.request.Login;
import com.epam.socode.request.Logout;
import com.epam.socode.request.Signup;
import com.epam.socode.response.Response;
import com.epam.socode.util.Utils;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

/**
 * @author jafar_qaddoumi
 */
class BaseControllerTest {
    static final String password = "my_crazy_password";
    static final ObjectMapper mapper = Utils.appObjectMapper();

    @Autowired
    WebApplicationContext appContext;

    MockMvc mockMvc;

    @Before
    public void setup() throws Exception {
        mockMvc = MockMvcBuilders.webAppContextSetup(appContext)
                .alwaysExpect(MockMvcResultMatchers.status().isOk()).build();
    }

    Response getResponseFromResult(MvcResult result) throws Exception {
        String content = result.getResponse().getContentAsString();
        return mapper.readValue(content, Response.class);
    }

    MvcResult signUpProfile(String email, WorkGroup project) throws Exception {
        Signup signup = new Signup();
        signup.setLogin(email);
        signup.setPassword(password);
        if (project != null) {
            signup.setGroup(project.getGroupId());
        }

        return mockMvc.perform(
                MockMvcRequestBuilders.post(AuthController.MAPPING_API_AUTH + AuthController.MAPPING_SIGNUP)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(signup))
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print()).andReturn();
    }

    MvcResult loginProfile(Profile profile, String password) throws Exception {
        Login login = new Login();
        login.setLogin(profile.getEmail());
        login.setPassword(password);
        return mockMvc.perform(
                MockMvcRequestBuilders.post(AuthController.MAPPING_API_AUTH + AuthController.MAPPING_LOGIN)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(login))
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print()).andReturn();
    }

    MvcResult logoutProfile(String token) throws Exception {
        Logout logout = new Logout();
        logout.setToken(token);
        return mockMvc.perform(
                MockMvcRequestBuilders.post(AuthController.MAPPING_API_AUTH + AuthController.MAPPING_LOGOUT)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(logout))
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print()).andReturn();
    }

    MvcResult loginProfile(Profile profile) throws Exception {
        return loginProfile(profile, password);
    }

    Profile getProfileFromResponse(Response response) {
        return mapper.convertValue(response.getData(), Profile.class);
    }

    String getStringFromResponse(Response response) {
        return mapper.convertValue(response.getData(), String.class);
    }
}
