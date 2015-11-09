package com.epam.socode.domain;

import com.epam.socode.request.Signup;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author jafar_qaddoumi
 */
@Entity
@Table(name = Profile.TABLE)
public class Profile {
    public static final String TABLE = "PROFILES";
    public static final String COLUMN_ID = "PROFILE_ID";
    public static final String COLUMN_PARTICIPATED_GROUPS = "PARTICIPATED_GROUPS";

    @Id
    @Column(name = Profile.COLUMN_ID)
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @JsonProperty("profile_id")
    private String profileId;

    @JsonProperty("name")
    private String name;

    @JsonProperty("surname")
    private String surname;

    @JsonProperty("email")
    @Column(unique = true, nullable = false)
    private String email;

    @JsonProperty("enabled")
    private boolean enabled;

    @JsonIgnore
    @Column(nullable = false)
    private String password;

    @JsonProperty("main_language")
    private String mainLanguage;

    @JsonProperty("join_date")
    private String joinDate;

    @JsonProperty("comment_likes")
    private Long commentLikes;

    @JsonProperty("total_score")
    private Long totalScore;

    @Column(name = Profile.COLUMN_PARTICIPATED_GROUPS)
    @ManyToMany(
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            targetEntity = WorkGroup.class)
    @JoinTable(
            name = Profile.TABLE + "_" + WorkGroup.TABLE,
            joinColumns = @JoinColumn(name = Profile.COLUMN_ID),
            inverseJoinColumns = @JoinColumn(name = WorkGroup.COLUMN_ID))
    @JsonProperty("participated")
    public List<WorkGroup> participatedWorkGroup = new ArrayList<>();

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

    public List<WorkGroup> getParticipatedGroups() {
        return participatedWorkGroup;
    }

    public void setParticipatedWorkGroup(List<WorkGroup> participatedWorkGroup) {
        this.participatedWorkGroup = participatedWorkGroup;
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

    public void addParticipatedGroup(WorkGroup participatedWorkGroup) {
        this.participatedWorkGroup.add(participatedWorkGroup);
    }
}
