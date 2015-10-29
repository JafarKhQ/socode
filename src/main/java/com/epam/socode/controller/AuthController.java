package com.epam.socode.controller;

import com.epam.socode.domain.Profile;
import com.epam.socode.event.OnRegistrationCompleteEvent;
import com.epam.socode.request.Login;
import com.epam.socode.request.Logout;
import com.epam.socode.request.Signup;
import com.epam.socode.request.Verify;
import com.epam.socode.response.Response;
import com.epam.socode.service.AuthenticationService;
import com.epam.socode.service.ProfileService;
import com.epam.socode.service.ProjectService;
import com.epam.socode.service.VerificationTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
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

    @Autowired
    private ProjectService projectService;

    @Autowired
    private VerificationTokenService verificationTokenService;

    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private ApplicationEventPublisher eventPublisher;

    @RequestMapping(value = MAPPING_SIGNUP,
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public Response handleSignup(@RequestBody Signup signup) {
        //TODO: remove test project
        projectService.addProject("Test Project");
        Profile result = profileService.addProfileFromSignup(signup);
        eventPublisher.publishEvent(new OnRegistrationCompleteEvent(result));
        return Response.newSuccessResponse(result);
    }

    @RequestMapping(value = MAPPING_VERIFY,
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public Response handleVerify(@RequestBody Verify verify) {
        Profile profile = verificationTokenService.verifyProfile(verify);
        return Response.newSuccessResponse(profile);
    }

    @RequestMapping(value = MAPPING_LOGIN,
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public Response handleLogin(@RequestBody Login login) {
        String token = authenticationService.login(login);
        return Response.newSuccessResponse(token);
    }

    @RequestMapping(value = MAPPING_LOGOUT,
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public Response handleLogout(@RequestBody Logout logout) {
        authenticationService.logout(logout);
        return Response.newSuccessResponse();
    }
}
