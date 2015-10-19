package com.epam.socode.repository.impl;

import com.epam.socode.domain.Profile;
import com.epam.socode.repository.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 * @author jafar_qaddoumi
 */
@Repository
public class ProfileRepositoryImpl implements ProfileRepository {

    @Autowired
    private EntityManagerFactory entityManagerFactory;

    @Override
    public Profile addProfile(Profile profile) {
        EntityManager em = entityManagerFactory.createEntityManager();

        em.getTransaction().begin();
        em.persist(profile);
        em.getTransaction().commit();
        em.close();

        //TODO: return the stored object
        return null;

        // get a new EM to make sure data is actually retrieved from the store
        // and not Hibernate's internal cache
//        em = entityManagerFactory.createEntityManager();
//        Profile storedProfile = em.find(Profile.class, );
//        em.close();
//
//        return storedProfile;
    }
}
