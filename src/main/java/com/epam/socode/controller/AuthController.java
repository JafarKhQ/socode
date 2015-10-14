package com.epam.socode.controller;

import com.epam.socode.request.Signup;
import com.epam.socode.response.Response;
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

        return Response.newSuccessResponse(signup);
    }
}
