package com.epam.socode.repository.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import com.epam.socode.domain.VerificationToken;
import com.epam.socode.repository.TokenRepository;

/**
 * 
 * @author Krystian_Balwierz
 *
 */
@Repository
public class TokenRepositoryImpl implements TokenRepository {

	@Autowired
	EntityManagerFactory entityManagerFactory;

	@Override
	public void save(VerificationToken verificationToken) {
		EntityManager em = entityManagerFactory.createEntityManager();
		em.getTransaction().begin();
		em.persist(verificationToken);
		em.getTransaction().commit();
		em.close();
	}

	@Override
	public VerificationToken getByVerificationKey(String token) {
		EntityManager em = entityManagerFactory.createEntityManager();

		CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
		CriteriaQuery<VerificationToken> criteriaQuery = criteriaBuilder.createQuery(VerificationToken.class);
		Root<VerificationToken> root = criteriaQuery.from(VerificationToken.class);
		criteriaQuery.select(root);

		ParameterExpression<String> params = criteriaBuilder.parameter(String.class);
		criteriaQuery.where(criteriaBuilder.equal(root.get("token"), params));

		TypedQuery<VerificationToken> query = em.createQuery(criteriaQuery);
		query.setParameter(params, token);

		List<VerificationToken> queryResult = query.getResultList();

		VerificationToken verificationToken = null;

		if (!CollectionUtils.isEmpty(queryResult)) {
			verificationToken = queryResult.get(0);
		}
		return verificationToken;
	}
}
