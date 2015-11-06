package com.epam.socode.repository.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.epam.socode.domain.CodeFragment;
import com.epam.socode.domain.Language;
import com.epam.socode.repository.CodeFragmentRepository;
import com.epam.socode.repository.LanguageRepository;

@Repository
public class LanguageRepositoryImpl implements LanguageRepository {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Language addLanguage(Language language) {
        Session session = sessionFactory.openSession();
        session.getTransaction().begin();
        session.persist(language);
        session.getTransaction().commit();
        session.close();
        return language;
    }

    @Override
    public Language findLanguageByName(String name) {
        // TODO Auto-generated method stub
        return null;
    }
}
