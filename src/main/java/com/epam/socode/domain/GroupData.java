package com.epam.socode.domain;

/**
 * Contain necessary information to create Group.
 * @author Lukasz_Wator
 *
 */
public class GroupData{
	private String groupName;
	private String groupOwnerId;
	
	
	public GroupData(String groupName, String groupOwnerId) {
		this.groupName = groupName;
		this.groupOwnerId = groupOwnerId;
	}

	public String getGroupName() {
		return groupName;
	}
	
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	
	public String getGroupOwnerId() {
		return groupOwnerId;
	}
	
	public void setGroupOwnerId(String groupOwnerId) {
		this.groupOwnerId = groupOwnerId;
	}
}
