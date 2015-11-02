package com.epam.socode.repository.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import com.epam.socode.domain.WorkGroup;
import com.epam.socode.repository.GroupRepository;

@Repository
public class GroupRepositoryImpl implements GroupRepository {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public WorkGroup findGroupById(String groupId) {
        WorkGroup workGroup = null;
        Session session = sessionFactory.openSession();
        Query q = session.createQuery("FROM WorkGroup p WHERE p.groupId = '" + groupId + "'");
        @SuppressWarnings("rawtypes")
        List queryResult = q.list();
        if (!CollectionUtils.isEmpty(queryResult)) {
            workGroup = (WorkGroup) queryResult.get(0);
        }
        session.close();
        return workGroup;
    }

    @Override
    public WorkGroup addGroup(WorkGroup workGroup) {
        Session session = sessionFactory.openSession();
        session.getTransaction().begin();
        session.persist(workGroup);
        session.getTransaction().commit();
        session.close();
        return workGroup;
    }
}
