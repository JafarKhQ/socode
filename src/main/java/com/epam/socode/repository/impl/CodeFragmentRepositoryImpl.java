package com.epam.socode.repository.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.epam.socode.domain.CodeFragment;
import com.epam.socode.repository.CodeFragmentRepository;

@Repository
public class CodeFragmentRepositoryImpl implements CodeFragmentRepository {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public CodeFragment addCodeFragment(CodeFragment codeFragment) {
        Session session = sessionFactory.openSession();
        session.getTransaction().begin();
        session.persist(codeFragment);
        session.getTransaction().commit();
        session.close();
        return codeFragment;
    }

    @Override
    public CodeFragment updateCodeFragment(CodeFragment codeFragment) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public CodeFragment findCodeFragmentByName(String name) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public CodeFragment findCodeFragmentById(String codeFragmentId) {
        // TODO Auto-generated method stub
        return null;
    }

}
