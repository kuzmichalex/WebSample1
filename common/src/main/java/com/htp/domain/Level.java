package com.htp.domain;

import java.util.Objects;

public class Level {
	private long id;
	private String name;
	private boolean isDeleted;

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Level)) return false;
		Level level = (Level) o;
		return id == level.id &&
				isDeleted == level.isDeleted &&
				Objects.equals(name, level.name);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, name, isDeleted);
	}

	@Override
	public String toString() {
		return "Level{" +
				"id=" + id +
				", name='" + name + '\'' +
				", isDeleted=" + isDeleted +
				'}';
	}

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

	public boolean isDeleted() {
		return isDeleted;
	}

	public void setDeleted(boolean deleted) {
		isDeleted = deleted;
	}
}
