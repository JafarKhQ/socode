package com.epam.socode.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.epam.socode.annotation.ControllerTest;
import com.epam.socode.domain.Group;

/**
 * @author jafar_qaddoumi
 */
@ControllerTest
@RunWith(SpringJUnit4ClassRunner.class)
public class GroupServiceTest {

    @Autowired
    private GroupService groupService;

    @Test
    public void testAddGroup() throws Exception {
        String groupName = "testAddGroup";
        Group group = groupService.addGroup(groupName);

        Assert.assertEquals(groupName, group.getGroupName());
    }

    @Test
    public void testFindGroupById() throws Exception {
        String groupName = "testFindGroupById";
        Group group = groupService.addGroup(groupName);

        Group groupFind = groupService.findGroupById(group.getGroupId());

        Assert.assertEquals(group.getGroupId(), groupFind.getGroupId());
        Assert.assertEquals(group.getGroupName(), groupFind.getGroupName());
    }
}