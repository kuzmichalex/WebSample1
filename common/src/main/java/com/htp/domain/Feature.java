package com.htp.domain;

import java.util.Objects;

public class Feature {
	private long id;
	private String description;
	private String name;
	private boolean isDeleted;

	@Override
	public String toString() {
		return "Feature{" +
				"id=" + id +
				", description='" + description + '\'' +
				", name='" + name + '\'' +
				", is_deleted=" + isDeleted +
				'}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Feature)) return false;
		Feature feature = (Feature) o;
		return id == feature.id &&
				isDeleted == feature.isDeleted &&
				Objects.equals(description, feature.description) &&
				Objects.equals(name, feature.name);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, description, name, isDeleted);
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isDeleted() {
		return isDeleted;
	}

	public void setDeleted(boolean deleted) {
		this.isDeleted = deleted;
	}
}
