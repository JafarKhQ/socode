package com.epam.socode.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import org.hibernate.annotations.GenericGenerator;

import com.epam.socode.request.Signup;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author jafar_qaddoumi
 */
@Entity
public class Profile {

	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	@JsonProperty("profile_id")
	private String profileId;

	@JsonProperty("name")
	private String name;

	@JsonProperty("surname")
	private String surname;

	@JsonProperty("email")
	private String email;

	@JsonProperty("enabled")
	private boolean enabled;

	@JsonIgnore
	private String password;

	@JsonProperty("main_language")
	private String mainLanguage;

	@JsonProperty("join_date")
	private String joinDate;

	@JsonProperty("comment_likes")
	private Long commentLikes;

	@JsonProperty("total_score")
	private Long totalScore;

	@ManyToMany(targetEntity = Project.class)
	@JsonProperty("participated")
	public List<Project> participatedProjects = new ArrayList<>();

	public Profile() {
	}

	public Profile(Signup signup) {
		email = signup.getLogin();
		password = signup.getPassword();
		enabled = false;
	}

	public String getProfileId() {
		return profileId;
	}

	public void setProfileId(String profileId) {
		this.profileId = profileId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getMainLanguage() {
		return mainLanguage;
	}

	public void setMainLanguage(String mainLanguage) {
		this.mainLanguage = mainLanguage;
	}

	public String getJoinDate() {
		return joinDate;
	}

	public void setJoinDate(String joinDate) {
		this.joinDate = joinDate;
	}

	public List<Project> getParticipatedProjects() {
		return participatedProjects;
	}

	public void setParticipatedProjects(List<Project> participatedProjects) {
		this.participatedProjects = participatedProjects;
	}

	public Long getCommentLikes() {
		return commentLikes;
	}

	public void setCommentLikes(Long commentLikes) {
		this.commentLikes = commentLikes;
	}

	public Long getTotalScore() {
		return totalScore;
	}

	public void setTotalScore(Long totalScore) {
		this.totalScore = totalScore;
	}
}
