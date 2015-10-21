package com.epam.socode.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.epam.socode.domain.Profile;
import com.epam.socode.event.OnRegistrationCompleteEvent;
import com.epam.socode.request.Signup;
import com.epam.socode.request.Verify;
import com.epam.socode.response.Response;
import com.epam.socode.service.ProfileService;
import com.epam.socode.service.ProjectService;
import com.epam.socode.service.VerificationTokenService;

/**
 * @author jafar_qaddoumi
 */
@RestController
@RequestMapping(BaseController.MAPPING_API_AUTH)
class AuthController implements BaseController {

    @Autowired
    private ProfileService profileService;

    @Autowired
    private ProjectService projectService;

    @Autowired
    private VerificationTokenService verificationTokenService;

    @Autowired
    ApplicationEventPublisher eventPublisher;

    @RequestMapping(value = "/test", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    public Response handleTest() {
        return Response.newSuccessResponse();
    }

    @RequestMapping(value = MAPPING_SIGNUP, method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Response handleSignup(@RequestBody Signup signup) {
        projectService.addProject("Test Project");
        Profile result = profileService.addProfileFromSignup(signup);
        eventPublisher.publishEvent(new OnRegistrationCompleteEvent(result));
        return Response.newSuccessResponse(result);
    }

    @RequestMapping(value = MAPPING_VERIFY, method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Response handleVerify(@RequestBody Verify verify) {
        Profile profile = verificationTokenService.verifyProfile(verify);
        return Response.newSuccessResponse(profile);
    }
}
