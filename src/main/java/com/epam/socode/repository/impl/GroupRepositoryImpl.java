package com.epam.socode.repository.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import com.epam.socode.domain.Group;
import com.epam.socode.repository.GroupRepository;

@Repository
public class GroupRepositoryImpl implements GroupRepository {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Group findGroupById(String groupId) {
        Group group = null;
        Session session = sessionFactory.openSession();
        Query q = session.createQuery("FROM Group p WHERE p.groupId = '" + groupId + "'");
        @SuppressWarnings("rawtypes")
        List queryResult = q.list();
        if (!CollectionUtils.isEmpty(queryResult)) {
            group = (Group) queryResult.get(0);
        }
        session.close();
        return group;
    }

    @Override
    public Group addGroup(Group group) {
        Session session = sessionFactory.openSession();
        session.getTransaction().begin();
        session.persist(group);
        session.getTransaction().commit();
        session.close();
        return group;
    }
}
