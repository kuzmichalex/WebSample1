package com.htp.domain;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.Objects;

/**
 * class that reflects m_user_history table entries
 */
public class UserHistory {

	private long id;
	private long userId;
	private Date date;
	private int weight;
	private int height;

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

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
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
