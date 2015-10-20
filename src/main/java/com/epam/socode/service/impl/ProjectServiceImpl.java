package com.epam.socode.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epam.socode.domain.Project;
import com.epam.socode.repository.ProjectRepository;
import com.epam.socode.service.ProjectService;

@Service
public class ProjectServiceImpl implements ProjectService {

	@Autowired
	private ProjectRepository projectRepository;

	@Override
	public Project addProject(String projectName) {
		return projectRepository.addProject(new Project(projectName));
	}

	@Override
	public Project findProjectById(String projectId) {
		return projectRepository.findProjectById(projectId);
	}

}
