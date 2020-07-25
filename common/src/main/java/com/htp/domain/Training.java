package com.htp.domain;

import java.util.Objects;

/**
 * class that reflects m_trainings table entries
 * */
public class Training {
	private long id;
	private String name;
	private String description;
	private long userAuthorId;

	public Training() { }

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

	public long getUserAuthorId() {
		return userAuthorId;
	}

	public void setUserAuthorId(long userAuthorId) {
		this.userAuthorId = userAuthorId;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Training)) return false;
		Training trainig = (Training) o;
		return id == trainig.id &&
				userAuthorId == trainig.userAuthorId &&
				name.equals(trainig.name) &&
				description.equals(trainig.description);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, name, description, userAuthorId);
	}
}
