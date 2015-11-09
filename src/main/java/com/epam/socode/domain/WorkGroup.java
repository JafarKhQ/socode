package com.epam.socode.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author jafar_qaddoumi
 */
@Entity
@Table(name = WorkGroup.TABLE)
public class WorkGroup {
    public static final String TABLE = "WORK_GROUPS";
    public static final String COLUMN_ID = "GROUPS_ID";

    @Id
    @Column(name = WorkGroup.COLUMN_ID)
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @JsonProperty("group_id")
    private String groupId;

    @JsonProperty("group_name")
    private String groupName;

    @JsonProperty("owner_profile_id")
    private String ownerProfileId;

    @ManyToMany(
            fetch = FetchType.LAZY,
            targetEntity = Profile.class,
            mappedBy = "participatedWorkGroup")
    private List<Profile> members = new ArrayList<>();

    public WorkGroup() {
    }

    public WorkGroup(String groupName) {
        this.groupName = groupName;
    }

    public WorkGroup(String groupName, String ownerProfileId) {
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

    public List<Profile> getMembers() {
        return members;
    }

    public void setMembers(List<Profile> members) {
        this.members = members;
    }
}
