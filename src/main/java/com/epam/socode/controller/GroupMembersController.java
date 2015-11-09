package com.epam.socode.controller;

import com.epam.socode.domain.GroupData;
import com.epam.socode.domain.WorkGroup;
import com.epam.socode.request.GroupCreate;
import com.epam.socode.response.Response;
import com.epam.socode.service.AuthenticationService;
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
@RequestMapping(BaseController.MAPPING_API_GROUP_MEMBERS)
class GroupMembersController implements BaseController {

    @Autowired
    private AuthenticationService authenticationService;


    /**
     * Any user should be able to add itself to group.
     * User owner should be able to add any other user to group.
     * If user owner added other user to group then that user is automatically enabled in that group.
     *
     * @param groupCreate RequestBody JSON object
     * @return Response
     */
    @RequestMapping(value = BaseController.MAPPING_ADD,
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public Response handleAddMember(@RequestBody GroupCreate groupCreate) {
        authenticationService.validateToken(groupCreate.getToken());
        String profileId = authenticationService.findProfileIdByToken(groupCreate.getToken());

        return Response.newSuccessResponse();
    }
}
