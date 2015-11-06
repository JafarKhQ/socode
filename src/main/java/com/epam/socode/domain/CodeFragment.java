package com.epam.socode.domain;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author Mateusz_Gawel
 *
 */
@Entity
public class CodeFragment {
    
    @JsonProperty("fragment_id")
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private UUID fragmentId;

    @JsonProperty("name")
    private String name;

    @JsonProperty("creation_time")
    @Type(type = "timestamp")
    private Date creationTime;

    @JsonProperty("modification_time")
    @Type(type = "timestamp")
    private Date modificationTime;

    @JsonProperty("body")
    @Type(type = "text")
    private String body;

    @JsonProperty("owner")
    @ManyToOne
    @JoinColumn(name = "profile_id")
    private Profile profile;

    @JsonProperty("languages")
    @ManyToMany
    @JoinTable(name = "CodeFragment_Language", joinColumns = { @JoinColumn(name = "fragment_id") }, inverseJoinColumns = {
            @JoinColumn(name = "language_id") })
    private List<Language> languages;

    @JsonProperty("tags")
    @ManyToMany(targetEntity = Tag.class, fetch = FetchType.EAGER)
    @JoinTable(name = "CodeFragment_Tag", joinColumns = { @JoinColumn(name = "fragment_id") }, inverseJoinColumns = {
            @JoinColumn(name = "tag_id") })
    private List<Tag> tags;

    @JsonProperty("acl")
    @ManyToMany(targetEntity = Tag.class, fetch = FetchType.EAGER)
    @JoinTable(name = "CodeFragment_AccessControl", joinColumns = {
            @JoinColumn(name = "fragment_id") }, inverseJoinColumns = { @JoinColumn(name = "access_control_id") })
    private List<AccessControl> acl;

    public UUID getFragmentId() {
        return fragmentId;
    }

    public void setFragmentId(UUID fragmentId) {
        this.fragmentId = fragmentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(Date creationTime) {
        this.creationTime = creationTime;
    }

    public Date getModificationTime() {
        return modificationTime;
    }

    public void setModificationTime(Date modificationTime) {
        this.modificationTime = modificationTime;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile owner) {
        this.profile = owner;
    }

    public List<Language> getLanguages() {
        return languages;
    }

    public void setLanguages(List<Language> languages) {
        this.languages = languages;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    public List<AccessControl> getAcl() {
        return acl;
    }

    public void setAcl(List<AccessControl> acl) {
        this.acl = acl;
    }
}
