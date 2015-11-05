package com.epam.socode.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epam.socode.domain.GroupData;
import com.epam.socode.domain.WorkGroup;
import com.epam.socode.exception.GroupExistException;
import com.epam.socode.repository.GroupRepository;
import com.epam.socode.service.GroupService;

@Service
public class GroupServiceImpl implements GroupService {

    @Autowired
    private GroupRepository groupRepository;
    

    @Override
    public WorkGroup addGroup(GroupData groupData) { 	
            WorkGroup p = findGroupByName(groupData.getGroupName());
            if (null != p) {
                throw new GroupExistException();
            }
        WorkGroup workGroup = new WorkGroup(groupData.getGroupName(),groupData.getGroupOwnerId());
        return groupRepository.addGroup(workGroup);
    }

    @Override
    public WorkGroup findGroupById(String groupId) {
        return groupRepository.findGroupById(groupId);
    }

	@Override
	public WorkGroup findGroupByName(String groupName) {
		return groupRepository.findGroupByName(groupName);
	}

	

}
