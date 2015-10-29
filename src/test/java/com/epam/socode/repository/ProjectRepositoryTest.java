package com.epam.socode.repository;

import com.epam.socode.annotation.ControllerTest;
import com.epam.socode.domain.Project;
import org.apache.logging.log4j.util.Strings;
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
public class ProjectRepositoryTest {

    @Autowired
    private ProjectRepository projectRepository;

    @Test
    public void testAddProject() throws Exception {
        Project project = new Project("testAddProject1");
        Project projectAdded = projectRepository.addProject(project);

        Assert.assertEquals(project.getProjectName(), projectAdded.getProjectName());
        Assert.assertFalse(Strings.isEmpty(projectAdded.getProjectId()));
    }

    @Test
    public void testFindProjectById() throws Exception {
        Project project = new Project("testFindProjectById2");
        project = projectRepository.addProject(project);

        Project projectFind = projectRepository.findProjectById(project.getProjectId());

        Assert.assertEquals(project.getProjectId(), projectFind.getProjectId());
        Assert.assertEquals(project.getProjectName(), projectFind.getProjectName());
    }
}