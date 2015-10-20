package com.epam.socode.repository.impl;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.epam.socode.domain.Project;
import com.epam.socode.repository.ProjectRepository;

public class ProjectRepositoryImpl implements ProjectRepository {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public Project findProjectById(String projectId) {
		Project project = null;
		return project;
	}
}
