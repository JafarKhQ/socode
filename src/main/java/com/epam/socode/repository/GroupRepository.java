package com.epam.socode.repository;

import com.epam.socode.domain.WorkGroup;

/**
 * @author jafar_qaddoumi
 */
public interface GroupRepository {
    WorkGroup addGroup(WorkGroup workGroup);

    WorkGroup findGroupById(String groupId);
    
    WorkGroup findGroupByName(String groupName);
}
