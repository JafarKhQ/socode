package com.epam.socode.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epam.socode.domain.Group;
import com.epam.socode.repository.GroupRepository;
import com.epam.socode.service.GroupService;

@Service
public class GroupServiceImpl implements GroupService {

    @Autowired
    private GroupRepository groupRepository;

    @Override
    public Group addGroup(String groupName) {
        return groupRepository.addGroup(new Group(groupName));
    }

    @Override
    public Group findGroupById(String groupId) {
        return groupRepository.findGroupById(groupId);
    }

}
