package com.htp.domain;

import java.sql.Date;
import java.util.Objects;

public class UserGroup {
	private long id;
	private long userId;
	private long groupId;
	private Date dateIn;
	private Date dateOut;
	private boolean isDeleted;

	@Override
	public String toString() {
		return "UserGroup{" +
				"id=" + id +
				", userId=" + userId +
				", groupId=" + groupId +
				", dateIn=" + dateIn +
				", dateOut=" + dateOut +
				", isDeleted=" + isDeleted +
				'}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof UserGroup)) return false;
		UserGroup userGroup = (UserGroup) o;
		return id == userGroup.id &&
				userId == userGroup.userId &&
				groupId == userGroup.groupId &&
				isDeleted == userGroup.isDeleted &&
				Objects.equals(dateIn, userGroup.dateIn) &&
				Objects.equals(dateOut, userGroup.dateOut);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, userId, groupId, dateIn, dateOut, isDeleted);
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public long getGroupId() {
		return groupId;
	}

	public void setGroupId(long groupId) {
		this.groupId = groupId;
	}

	public Date getDateIn() {
		return dateIn;
	}

	public void setDateIn(Date dateIn) {
		this.dateIn = dateIn;
	}

	public Date getDateOut() {
		return dateOut;
	}

	public void setDateOut(Date dateOut) {
		this.dateOut = dateOut;
	}

	public boolean isDeleted() {
		return isDeleted;
	}

	public void setDeleted(boolean deleted) {
		isDeleted = deleted;
	}
}
