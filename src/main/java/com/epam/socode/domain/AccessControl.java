package com.epam.socode.domain;

import java.util.List;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
public class AccessControl {

    @JsonProperty("acl_id")
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private UUID accessControlId;
    
    @JsonProperty("type")
    @Enumerated(EnumType.STRING)
    private Type type;
    
    @JsonIgnore
    private UUID typeKey;
    
    @JsonIgnore
    @ManyToMany(mappedBy="tags")
    private List<CodeFragment> codeFragments;
    
    
    
    
    private enum Type {
        USER, GROUP, ALL
    }
}
