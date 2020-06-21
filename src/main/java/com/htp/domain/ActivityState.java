package com.htp.domain;

import java.util.Objects;

public class ActivityState {
	private long id;
	private long stateName;
	private boolean isDeleted;

	public ActivityState() {
	}

	@Override
	public String toString() {
		return "ActivityState{" +
				"id=" + id +
				", stateName=" + stateName +
				", isDeleted=" + isDeleted +
				'}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof ActivityState)) return false;
		ActivityState that = (ActivityState) o;
		return id == that.id &&
				stateName == that.stateName &&
				isDeleted == that.isDeleted;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, stateName, isDeleted);
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getStateName() {
		return stateName;
	}

	public void setStateName(long stateName) {
		this.stateName = stateName;
	}

	public boolean isDeleted() {
		return isDeleted;
	}

	public void setDeleted(boolean deleted) {
		isDeleted = deleted;
	}
}
