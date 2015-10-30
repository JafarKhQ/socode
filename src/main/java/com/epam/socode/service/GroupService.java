package com.epam.socode.service;

import com.epam.socode.domain.Group;

/**
 * @author jafar_qaddoumi
 */
public interface GroupService {
    Group addGroup(String groupName);

    Group findGroupById(String groupId);
}
