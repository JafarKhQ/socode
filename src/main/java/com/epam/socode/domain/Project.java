package com.epam.socode.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author jafar_qaddoumi
 */
@Entity
public class Project {

	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	@JsonProperty("project_id")
	private String projectId;

	@JsonProperty("project_name")
	private String projectName;

	@ManyToMany(targetEntity = Profile.class, mappedBy = "participatedProjects")
	@JsonIgnore
	private List<Profile> participatedProfiles = new ArrayList<>();

	public String getProjectId() {
		return projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public List<Profile> getParticipatedProfiles() {
		return participatedProfiles;
	}

	public void setParticipatedProfiles(List<Profile> participatedProfiles) {
		this.participatedProfiles = participatedProfiles;
	}
}
