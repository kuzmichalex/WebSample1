package com.htp.domain;

import java.sql.Date;
import java.util.Objects;

public class Group {
	private long id;
	private String name;
	private String description;
	private long userFounderId;
	private Date dateFoundation;
	private boolean isDeleted;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public long getUserFounderId() {
		return userFounderId;
	}

	public void setUserFounderId(long userFounderId) {
		this.userFounderId = userFounderId;
	}

	public Date getDateFoundation() {
		return dateFoundation;
	}

	public void setDateFoundation(Date dateFoundation) {
		this.dateFoundation = dateFoundation;
	}

	public boolean isDeleted() {
		return isDeleted;
	}

	public void setDeleted(boolean deleted) {
		isDeleted = deleted;
	}


	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Group)) return false;
		Group group = (Group) o;
		return id == group.id &&
				userFounderId == group.userFounderId &&
				isDeleted == group.isDeleted &&
				Objects.equals(name, group.name) &&
				Objects.equals(description, group.description) &&
				Objects.equals(dateFoundation, group.dateFoundation);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, name, description, userFounderId, dateFoundation, isDeleted);
	}


}
