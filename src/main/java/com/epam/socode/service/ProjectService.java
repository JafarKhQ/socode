package com.epam.socode.service;

import com.epam.socode.domain.Project;

/**
 * @author jafar_qaddoumi
 */
public interface ProjectService {
    Project addProject(String projectName);

    Project findProjectById(String projectId);
}
