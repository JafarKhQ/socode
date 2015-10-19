package com.epam.socode.controller;

import com.epam.socode.domain.Profile;
import com.epam.socode.request.Signup;
import com.epam.socode.response.Response;
import com.epam.socode.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author jafar_qaddoumi
 */
@RestController
@RequestMapping(BaseController.MAPPING_API_AUTH)
class AuthController implements BaseController {

    @Autowired
    private ProfileService profileService;

    @RequestMapping(value = "/test",
            produces = MediaType.APPLICATION_JSON_VALUE,
            method = RequestMethod.GET)
    public Response handleTest() {
        return Response.newSuccessResponse();
    }

    @RequestMapping(value = MAPPING_SIGNUP,
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public Response handleSignup(@RequestBody Signup signup) {

        Profile result = profileService.addProfile(new Profile(signup));
        return Response.newSuccessResponse(result);
    }
}
