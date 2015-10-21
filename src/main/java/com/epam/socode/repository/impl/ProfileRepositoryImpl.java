package com.epam.socode.repository.impl;

import com.epam.socode.domain.Profile;
import com.epam.socode.excepion.ProfileNotFoundException;
import com.epam.socode.repository.ProfileRepository;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * @author jafar_qaddoumi
 */
@Repository
public class ProfileRepositoryImpl implements ProfileRepository {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Profile addProfile(Profile profile) {
        Session session = sessionFactory.openSession();
        session.getTransaction().begin();
        session.persist(profile);
        session.getTransaction().commit();
        session.close();
        return profile;
    }

    @Override
    public Profile updateProfile(Profile profile) {
        Session session = sessionFactory.openSession();
        session.getTransaction().begin();
        session.update(profile);
        session.getTransaction().commit();
        session.close();
        return profile;
    }

    @Override
    public Profile findProfileByEmail(String email) {
        Session session = sessionFactory.openSession();
        Query q = session.createQuery("FROM Profile p WHERE p.email = '" + email + "'");

        @SuppressWarnings("rawtypes")
        List queryResult = q.list();
        try {
            if (CollectionUtils.isEmpty(queryResult)) {
                throw new ProfileNotFoundException();
            }
            return (Profile) queryResult.get(0);
        } finally {
            session.close();
        }
    }

    @Override
    public Profile findProfileById(String profileId) {
        Session session = sessionFactory.openSession();
        session.getTransaction().begin();
        Query q = session.createQuery("FROM Profile p WHERE p.profileId = '" + profileId + "'");

        @SuppressWarnings("rawtypes")
        List queryResult = q.list();
        try {
            if (CollectionUtils.isEmpty(queryResult)) {
                throw new ProfileNotFoundException();
            }
            return (Profile) queryResult.get(0);
        } finally {
            session.close();
        }
    }
}
