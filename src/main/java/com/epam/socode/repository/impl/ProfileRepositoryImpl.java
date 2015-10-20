package com.epam.socode.repository.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.epam.socode.domain.Profile;
import com.epam.socode.repository.ProfileRepository;

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
		// session.refresh(profile);
		session.getTransaction().commit();

		return profile;
	}
}
