package com.epam.socode.service;

import com.epam.socode.domain.WorkGroup;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.epam.socode.annotation.ControllerTest;

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
        WorkGroup workGroup = groupService.addGroup(groupName);

        Assert.assertEquals(groupName, workGroup.getGroupName());
    }

    @Test
    public void testFindGroupById() throws Exception {
        String groupName = "testFindGroupById";
        WorkGroup workGroup = groupService.addGroup(groupName);

        WorkGroup workGroupFind = groupService.findGroupById(workGroup.getGroupId());

        Assert.assertEquals(workGroup.getGroupId(), workGroupFind.getGroupId());
        Assert.assertEquals(workGroup.getGroupName(), workGroupFind.getGroupName());
    }
}