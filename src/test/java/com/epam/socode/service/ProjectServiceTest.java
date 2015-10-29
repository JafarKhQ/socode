package com.epam.socode.service;

import com.epam.socode.annotation.ControllerTest;
import com.epam.socode.domain.Project;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author jafar_qaddoumi
 */
@ControllerTest
@RunWith(SpringJUnit4ClassRunner.class)
public class ProjectServiceTest {

    @Autowired
    private ProjectService projectService;

    @Test
    public void testAddProject() throws Exception {
        String projectName = "testAddProject";
        Project project = projectService.addProject(projectName);

        Assert.assertEquals(projectName, project.getProjectName());
    }

    @Test
    public void testFindProjectById() throws Exception {
        String projectName = "testFindProjectById";
        Project project = projectService.addProject(projectName);

        Project projectFind = projectService.findProjectById(project.getProjectId());

        Assert.assertEquals(project.getProjectId(), projectFind.getProjectId());
        Assert.assertEquals(project.getProjectName(), projectFind.getProjectName());
    }
}