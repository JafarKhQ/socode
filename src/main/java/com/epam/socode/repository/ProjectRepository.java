package com.epam.socode.repository;

import com.epam.socode.domain.Project;

/**
 * @author jafar_qaddoumi
 */
public interface ProjectRepository {
    Project addProject(Project project);

    Project findProjectById(String projectId);
}
