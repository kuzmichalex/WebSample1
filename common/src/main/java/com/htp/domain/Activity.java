package com.htp.domain;

import java.sql.Date;
import java.util.Objects;

public class Activity {
	private long id;
	private long userId;
	private long groupId;
	private long levelId;
	private Date timeStart;
	private Date timeEnd;
	private long stateId;
	private long trainingId;
	private boolean isDeleted;

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Activity)) return false;
		Activity activity = (Activity) o;
		return id == activity.id &&
				userId == activity.userId &&
				groupId == activity.groupId &&
				levelId == activity.levelId &&
				stateId == activity.stateId &&
				trainingId == activity.trainingId &&
				isDeleted == activity.isDeleted &&
				Objects.equals(timeStart, activity.timeStart) &&
				Objects.equals(timeEnd, activity.timeEnd);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, userId, groupId, levelId, timeStart, timeEnd, stateId, trainingId, isDeleted);
	}

	@Override
	public String toString() {
		return "Activity{" +
				"id=" + id +
				", userId=" + userId +
				", groupId=" + groupId +
				", levelId=" + levelId +
				", timeStart=" + timeStart +
				", timeEnd=" + timeEnd +
				", stateId=" + stateId +
				", trainingId=" + trainingId +
				", isDeleted=" + isDeleted +
				'}';
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

	public long getLevelId() {
		return levelId;
	}

	public void setLevelId(long levelId) {
		this.levelId = levelId;
	}

	public Date getTimeStart() {
		return timeStart;
	}

	public void setTimeStart(Date timeStart) {
		this.timeStart = timeStart;
	}

	public Date getTimeEnd() {
		return timeEnd;
	}

	public void setTimeEnd(Date timeEnd) {
		this.timeEnd = timeEnd;
	}

	public long getStateId() {
		return stateId;
	}

	public void setStateId(long stateId) {
		this.stateId = stateId;
	}

	public long getTrainingId() {
		return trainingId;
	}

	public void setTrainingId(long trainingId) {
		this.trainingId = trainingId;
	}

	public boolean isDeleted() {
		return isDeleted;
	}

	public void setDeleted(boolean deleted) {
		isDeleted = deleted;
	}
}
