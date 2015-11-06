package com.epam.socode.repository.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.epam.socode.domain.CodeFragment;
import com.epam.socode.domain.Language;
import com.epam.socode.domain.Tag;
import com.epam.socode.repository.CodeFragmentRepository;
import com.epam.socode.repository.LanguageRepository;
import com.epam.socode.repository.TagRepository;

@Repository
public class TagRepositoryImpl implements TagRepository {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Tag addTag(Tag tag) {
        Session session = sessionFactory.openSession();
        session.getTransaction().begin();
        session.persist(tag);
        session.getTransaction().commit();
        session.close();
        return tag;
    }

    @Override
    public Tag findTagByName(String name) {
        // TODO Auto-generated method stub
        return null;
    }
}
