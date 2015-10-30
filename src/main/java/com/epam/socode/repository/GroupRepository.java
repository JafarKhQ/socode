package com.epam.socode.repository;

import com.epam.socode.domain.Group;

/**
 * @author jafar_qaddoumi
 */
public interface GroupRepository {
    Group addGroup(Group group);

    Group findGroupById(String groupId);
}
