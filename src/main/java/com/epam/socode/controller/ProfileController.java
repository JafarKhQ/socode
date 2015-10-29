package com.epam.socode.controller;

import com.epam.socode.domain.Profile;
import com.epam.socode.request.ProfileData;
import com.epam.socode.request.ProfileUpdate;
import com.epam.socode.response.Response;
import com.epam.socode.service.AuthenticationService;
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
@RequestMapping(BaseController.MAPPING_API_AUTH_PROFILE)
class ProfileController implements BaseController {

    @Autowired
    private ProfileService profileService;
    @Autowired
    private AuthenticationService authenticationService;

    @RequestMapping(method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public Response handleProfileData(@RequestBody ProfileData profileData) {
        authenticationService.validateToken(profileData.getToken());
        Profile profile = profileService.findProfileById(profileData.getProfileId());
        return Response.newSuccessResponse(profile);
    }

    @RequestMapping(value = BaseController.MAPPING_UPDATE,
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public Response handleProfileUpdate(@RequestBody ProfileUpdate profileUpdate) {
        authenticationService.validateToken(profileUpdate.getToken());
        Profile profile = profileService.updateProfile(profileUpdate);
        return Response.newSuccessResponse(profile);
    }
}
