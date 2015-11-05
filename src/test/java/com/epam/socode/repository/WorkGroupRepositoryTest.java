package com.epam.socode.repository;

import org.apache.logging.log4j.util.Strings;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.epam.socode.annotation.ControllerTest;
import com.epam.socode.domain.WorkGroup;

/**
 * @author jafar_qaddoumi
 */
@ControllerTest
@RunWith(SpringJUnit4ClassRunner.class)
public class WorkGroupRepositoryTest {

    @Autowired
    private GroupRepository groupRepository;

    @Test
    public void testAddGroup() throws Exception {
        WorkGroup workGroup = new WorkGroup("testAddgroup1");
        WorkGroup workGroupAdded = groupRepository.addGroup(workGroup);

        Assert.assertEquals(workGroup.getGroupName(), workGroupAdded.getGroupName());
        Assert.assertFalse(Strings.isEmpty(workGroupAdded.getGroupId()));
    }

    @Test
    public void testFindGroupById() throws Exception {
        WorkGroup workGroup = new WorkGroup("testFindgroupById2");
        workGroup = groupRepository.addGroup(workGroup);

        WorkGroup workGroupFind = groupRepository.findGroupById(workGroup.getGroupId());

        Assert.assertEquals(workGroup.getGroupId(), workGroupFind.getGroupId());
        Assert.assertEquals(workGroup.getGroupName(), workGroupFind.getGroupName());
    }
}