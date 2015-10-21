package com.epam.socode.repository.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import com.epam.socode.domain.VerificationToken;
import com.epam.socode.repository.VerificationTokenRepository;

/**
 * 
 * @author Krystian_Balwierz
 *
 */
@Repository
public class VerificationTokenRepositoryImpl implements VerificationTokenRepository {

    @Autowired
    SessionFactory sessionFactory;

    @Override
    public VerificationToken addVerificationToken(VerificationToken verificationToken) {
        Session session = sessionFactory.openSession();
        session.getTransaction().begin();
        session.persist(verificationToken);
        session.getTransaction().commit();
        session.close();
        return verificationToken;
    }

    @Override
    public VerificationToken findVerificationTokenByKey(String verificationKey) {
        VerificationToken verificationToken = null;
        Session session = sessionFactory.openSession();
        Query q = session.createQuery("FROM VerificationToken v WHERE v.token = '" + verificationKey + "'");
        @SuppressWarnings("rawtypes")
        List queryResult = q.list();
        if (!CollectionUtils.isEmpty(queryResult)) {
            verificationToken = (VerificationToken) queryResult.get(0);
        }
        session.close();
        return verificationToken;
    }
}
