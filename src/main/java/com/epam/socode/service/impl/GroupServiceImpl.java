package com.epam.socode.service.impl;

import com.epam.socode.domain.WorkGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epam.socode.repository.GroupRepository;
import com.epam.socode.service.GroupService;

@Service
public class GroupServiceImpl implements GroupService {

    @Autowired
    private GroupRepository groupRepository;

    @Override
    public WorkGroup addGroup(String groupName) {
        return groupRepository.addGroup(new WorkGroup(groupName));
    }

    @Override
    public WorkGroup findGroupById(String groupId) {
        return groupRepository.findGroupById(groupId);
    }

}
