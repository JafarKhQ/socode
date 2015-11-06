package com.epam.socode.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.epam.socode.domain.CodeFragment;
import com.epam.socode.domain.Profile;
import com.epam.socode.request.CodeFragmentData;
import com.epam.socode.response.Response;
import com.epam.socode.service.AuthenticationService;
import com.epam.socode.service.CodeFragmentService;
import com.epam.socode.service.ProfileService;

/**
 * @author Mateusz_Gawel
 *
 */
@RestController
@RequestMapping(BaseController.MAPPING_API_CODE)
public class CodeFragmentController implements BaseController {

    @Autowired
    private CodeFragmentService codeFragmentService;
    @Autowired
    private AuthenticationService authenticationService;

    
    @RequestMapping(value = MAPPING_API_CODE_CREATE, method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Response handleCodeFragmentCreation(@RequestBody CodeFragmentData codeFragmentData) {
        authenticationService.validateToken(codeFragmentData.getToken());
        CodeFragment codeFragment = codeFragmentService.create(codeFragmentData);
        return Response.newSuccessResponse(codeFragment);
    }
    
}
