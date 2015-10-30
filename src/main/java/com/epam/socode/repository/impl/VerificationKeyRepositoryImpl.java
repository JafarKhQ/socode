package com.epam.socode.repository.impl;

import com.epam.socode.domain.VerificationKey;
import com.epam.socode.repository.VerificationKeyRepository;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * @author Krystian_Balwierz
 */
@Repository
public class VerificationKeyRepositoryImpl implements VerificationKeyRepository {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public VerificationKey addVerificationKey(VerificationKey verificationKey) {
        Session session = sessionFactory.openSession();
        session.getTransaction().begin();
        session.persist(verificationKey);
        session.getTransaction().commit();
        session.close();
        return verificationKey;
    }

    @Override
    public VerificationKey findVerificationKeyByKey(String key) {
        VerificationKey verificationToken = null;
        Session session = sessionFactory.openSession();
        Query q = session.createQuery("FROM VerificationKey v WHERE v.key = '" + key + "'");
        @SuppressWarnings("rawtypes")
        List queryResult = q.list();
        if (!CollectionUtils.isEmpty(queryResult)) {
            verificationToken = (VerificationKey) queryResult.get(0);
        }
        session.close();
        return verificationToken;
    }

    @Override
    public VerificationKey findVerificationKeyByProfileId(String profileId) {
        VerificationKey verificationKey = null;
        Session session = sessionFactory.openSession();
        Query q = session.createQuery("FROM VerificationKey v WHERE v.profile = '" + profileId + "'");
        @SuppressWarnings("rawtypes")
        List queryResult = q.list();
        if (!CollectionUtils.isEmpty(queryResult)) {
            verificationKey = (VerificationKey) queryResult.get(0);
        }
        session.close();
        return verificationKey;
    }
}
