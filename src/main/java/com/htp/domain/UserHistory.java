package com.htp.domain;

import java.sql.Timestamp;
import java.util.Objects;

/**
 * class that reflects m_user_history table entries
 */
public class UserHistory {

	private long id;
	private long userId;
	private Timestamp date;
	private long weight;
	private long height;

	public UserHistory() {
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

	public Timestamp getDate() {
		return date;
	}

	public void setDate(Timestamp date) {
		this.date = date;
	}

	public long getWeight() {
		return weight;
	}

	public void setWeight(long weight) {
		this.weight = weight;
	}

	public long getHeight() {
		return height;
	}

	public void setHeight(long height) {
		this.height = height;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof UserHistory)) return false;
		UserHistory that = (UserHistory) o;
		return id == that.id &&
				userId == that.userId &&
				weight == that.weight &&
				height == that.height &&
				Objects.equals(date, that.date);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, userId, date, weight, height);
	}

	@Override
	public String toString() {
		return "UserHistory{" +
				"id=" + id +
				", userId=" + userId +
				", date=" + date +
				", weight=" + weight +
				", height=" + height +
				'}';
	}
}
