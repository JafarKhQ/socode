package com.epam.socode.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.epam.socode.domain.GroupData;
import com.epam.socode.domain.WorkGroup;
import com.epam.socode.request.GroupCreate;
import com.epam.socode.response.Response;
import com.epam.socode.service.AuthenticationService;
import com.epam.socode.service.GroupService;

@RestController
@RequestMapping(BaseController.MAPPING_API_GROUP)
class GroupController implements BaseController {

    @Autowired
    private GroupService groupService;
    @Autowired
    private AuthenticationService authenticationService;


    @RequestMapping(value = BaseController.MAPPING_API_GROUP_CREATE,
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public Response handleProfileUpdate(@RequestBody GroupCreate groupCreate) {
        authenticationService.validateToken(groupCreate.getToken());
        String profileId  = authenticationService.findProfileIdByToken(groupCreate.getToken());
        WorkGroup workGroup = groupService.addGroup(new GroupData(groupCreate.getGroupName(),profileId));
        
        return Response.newSuccessResponse(workGroup);
    }
}
