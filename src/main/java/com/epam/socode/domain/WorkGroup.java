package com.epam.socode.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author jafar_qaddoumi
 */
@Entity
public class WorkGroup {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @JsonProperty("group_id")
    private String groupId;

    @JsonProperty("group_name")
    private String groupName;
    
    @JsonProperty("owner_profile_id")
    private String ownerProfileId;
    
   

	public WorkGroup() {
    }
	
	public WorkGroup(String groupName) {
        this.groupName = groupName;
    }
	
    public WorkGroup(String groupName,String ownerProfileId) {
        this.groupName = groupName;
        this.ownerProfileId = ownerProfileId;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }
    
    public String getOwnerProfileId() {
		return ownerProfileId;
	}

	public void setOwnerProfileID(String ownerProfileId) {
		this.ownerProfileId = ownerProfileId;
	}
}
