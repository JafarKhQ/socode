package com.epam.socode.controller;

import static org.junit.Assert.assertEquals;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import com.epam.socode.annotation.ControllerTest;
import com.epam.socode.domain.Profile;
import com.epam.socode.request.GroupCreate;
import com.epam.socode.response.ErrorCodes;
import com.epam.socode.response.Response;


@ControllerTest
@RunWith(SpringJUnit4ClassRunner.class)
public class GroupControllerTest extends BaseControllerTest {
	
	@InjectMocks
    GroupController groupController;
	
	@Test
	public void shouldFailIfUserIsNotLoggedTest() throws Exception{
		String groupName = "nonExistingGroup";
	 	String token = "invalidToken";
	 	
		MvcResult result= createGroup(groupName, token);
		
		Response response = getResponseFromResult(result);
		Assert.assertNotNull(response);
		Assert.assertEquals(Response.STATUS_FAIL, response.getStatus());
		Assert.assertEquals(ErrorCodes.INVALID_TOKEN_ERROR, response.getStatusCode());
	}
	@Test
	public void successfulGroupAddedTest() throws Exception{
		String email = "some_email@epam.com";
		String groupName = "thisGroupShouldBeCreated";
	 	String token = prepareUserAccountAndReturnToken(email);
        
	 	MvcResult result= createGroup(groupName, token);

        Response response = getResponseFromResult(result);
        assertEquals(Response.STATUS_SUCCESS, response.getStatus());
        assertEquals(Response.CODE_SUCCESS, response.getStatusCode());
	}
	
	@Test
	public void shouldNotCreateTheSameGroupTest() throws Exception{
		String email = "another_email@epam.com";
		String token = prepareUserAccountAndReturnToken(email);
		String groupName = "thisGroupShouldNotBeCreated";
        createGroup(groupName, token);
        
        MvcResult result= createGroup(groupName, token);

        Response response = getResponseFromResult(result);
        assertEquals(Response.STATUS_FAIL, response.getStatus());
        assertEquals(ErrorCodes.GROUP_EXIST_ERROR, response.getStatusCode());
	}
	
	private MvcResult createGroup(String groupName, String token) throws  Exception{
		
        GroupCreate groupCreate = new GroupCreate();
        groupCreate.setToken(token);
        groupCreate.setGroupName(groupName);
        
        MvcResult result = mockMvc.perform(
                MockMvcRequestBuilders.post(GroupController.MAPPING_API_GROUP+GroupController.MAPPING_CREATE)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(groupCreate))
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print()).andReturn();
        return result;
	}
	private String prepareUserAccountAndReturnToken(String email) throws Exception {
		Profile profile = getProfileFromResponse(
		        getResponseFromResult(
		                signUpProfile(email, null)
		        )
		);

		String token = getStringFromResponse(
		        getResponseFromResult(
		                loginProfile(profile)
		        )
		);
		return token;
	}
}
