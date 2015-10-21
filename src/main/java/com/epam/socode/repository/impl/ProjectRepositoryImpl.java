package com.epam.socode.repository.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import com.epam.socode.domain.Project;
import com.epam.socode.repository.ProjectRepository;

@Repository
public class ProjectRepositoryImpl implements ProjectRepository {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Project findProjectById(String projectId) {
        Project project = null;
        Session session = sessionFactory.openSession();
        Query q = session.createQuery("FROM Project p WHERE p.projectId = '" + projectId + "'");
        @SuppressWarnings("rawtypes")
        List queryResult = q.list();
        if (!CollectionUtils.isEmpty(queryResult)) {
            project = (Project) queryResult.get(0);
        }
        session.close();
        return project;
    }

    @Override
    public Project addProject(Project project) {
        Session session = sessionFactory.openSession();
        session.getTransaction().begin();
        session.persist(project);
        session.getTransaction().commit();
        session.close();
        return project;
    }
}
