package com.epam.socode.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.epam.socode.annotation.ControllerTest;
import com.epam.socode.domain.GroupData;
import com.epam.socode.domain.WorkGroup;

/**
 * @author jafar_qaddoumi
 */
@ControllerTest
@RunWith(SpringJUnit4ClassRunner.class)
public class WorkGroupServiceTest {

    @Autowired
    private GroupService groupService;

    @Test
    public void testAddGroup() throws Exception {
        String groupName = "testAddGroup";
        String groupOwnerId = "testGroupOwnerId";
        
        WorkGroup workGroup = groupService.addGroup(new GroupData(groupName, groupOwnerId));

        Assert.assertEquals(groupName, workGroup.getGroupName());
    }

    @Test
    public void testFindGroupById() throws Exception {
        String groupName = "testFindGroupById";
        String groupOwnerId = "testGroupOwnerId";       			
        WorkGroup workGroup = groupService.addGroup(new GroupData(groupName, groupOwnerId));

        WorkGroup workGroupFind = groupService.findGroupById(workGroup.getGroupId());

        Assert.assertEquals(workGroup.getGroupId(), workGroupFind.getGroupId());
        Assert.assertEquals(workGroup.getGroupName(), workGroupFind.getGroupName());
    }
}