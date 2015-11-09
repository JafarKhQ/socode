package com.epam.socode.service;

import com.epam.socode.domain.GroupData;
import com.epam.socode.domain.WorkGroup;

/**
 * @author jafar_qaddoumi
 */
public interface GroupService {

    WorkGroup findGroupById(String groupId); 
    
    WorkGroup findGroupByName(String groupName);

	WorkGroup addGroup(GroupData groupData);
}
