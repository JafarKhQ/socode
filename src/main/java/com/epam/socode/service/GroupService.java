package com.epam.socode.service;

import com.epam.socode.domain.WorkGroup;

/**
 * @author jafar_qaddoumi
 */
public interface GroupService {
    WorkGroup addGroup(String groupName);

    WorkGroup findGroupById(String groupId);
}
