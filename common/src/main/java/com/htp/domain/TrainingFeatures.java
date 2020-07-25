package com.htp.domain;

import java.util.Objects;

public class TrainingFeatures {
	private long id;
	private long trainingId;
	private long featureId;
	private boolean isDeleted;

	@Override
	public String toString() {
		return "TrainingFeatures{" +
				"id=" + id +
				", trainingId=" + trainingId +
				", featureId=" + featureId +
				", isDeleted=" + isDeleted +
				'}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof TrainingFeatures)) return false;
		TrainingFeatures that = (TrainingFeatures) o;
		return id == that.id &&
				trainingId == that.trainingId &&
				featureId == that.featureId &&
				isDeleted == that.isDeleted;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, trainingId, featureId, isDeleted);
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

	public long getFeatureId() {
		return featureId;
	}

	public void setFeatureId(long featureId) {
		this.featureId = featureId;
	}

	public boolean isDeleted() {
		return isDeleted;
	}

	public void setDeleted(boolean deleted) {
		isDeleted = deleted;
	}
}
