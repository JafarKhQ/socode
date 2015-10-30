package com.epam.socode.repository;

import org.apache.logging.log4j.util.Strings;
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
public class GroupRepositoryTest {

    @Autowired
    private GroupRepository groupRepository;

    @Test
    public void testAddGroup() throws Exception {
        Group group = new Group("testAddgroup1");
        Group groupAdded = groupRepository.addGroup(group);

        Assert.assertEquals(group.getGroupName(), groupAdded.getGroupName());
        Assert.assertFalse(Strings.isEmpty(groupAdded.getGroupId()));
    }

    @Test
    public void testFindGroupById() throws Exception {
        Group group = new Group("testFindgroupById2");
        group = groupRepository.addGroup(group);

        Group groupFind = groupRepository.findGroupById(group.getGroupId());

        Assert.assertEquals(group.getGroupId(), groupFind.getGroupId());
        Assert.assertEquals(group.getGroupName(), groupFind.getGroupName());
    }
}