package com.htp.domain;

import java.util.Objects;

public class TrainingLevel {
	private long id;
	private long trainingId;
	private long levelId;
	private int repetitionsMin;
	private int repetitionsMax;
	private String description;
	private int timeMin;
	private int timeMax;
	private boolean isDeleted;

	@Override
	public String toString() {
		return "TrainingLevel{" +
				"id=" + id +
				", trainingId=" + trainingId +
				", levelId=" + levelId +
				", repetitionsMin=" + repetitionsMin +
				", repetitionsMax=" + repetitionsMax +
				", description='" + description + '\'' +
				", timeMin=" + timeMin +
				", timeMax=" + timeMax +
				", isDeleted=" + isDeleted +
				'}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof TrainingLevel)) return false;
		TrainingLevel that = (TrainingLevel) o;
		return id == that.id &&
				trainingId == that.trainingId &&
				levelId == that.levelId &&
				repetitionsMin == that.repetitionsMin &&
				repetitionsMax == that.repetitionsMax &&
				timeMin == that.timeMin &&
				timeMax == that.timeMax &&
				isDeleted == that.isDeleted &&
				Objects.equals(description, that.description);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, trainingId, levelId, repetitionsMin, repetitionsMax, description, timeMin, timeMax, isDeleted);
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getTrainingId() {
		return trainingId;
	}

	public void setTrainingId(long trainingId) {
		this.trainingId = trainingId;
	}

	public long getLevelId() {
		return levelId;
	}

	public void setLevelId(long levelId) {
		this.levelId = levelId;
	}

	public int getRepetitionsMin() {
		return repetitionsMin;
	}

	public void setRepetitionsMin(int repetitionsMin) {
		this.repetitionsMin = repetitionsMin;
	}

	public int getRepetitionsMax() {
		return repetitionsMax;
	}

	public void setRepetitionsMax(int repetitionsMax) {
		this.repetitionsMax = repetitionsMax;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getTimeMin() {
		return timeMin;
	}

	public void setTimeMin(int timeMin) {
		this.timeMin = timeMin;
	}

	public int getTimeMax() {
		return timeMax;
	}

	public void setTimeMax(int timeMax) {
		this.timeMax = timeMax;
	}

	public boolean isDeleted() {
		return isDeleted;
	}

	public void setDeleted(boolean deleted) {
		isDeleted = deleted;
	}
}
