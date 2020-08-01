package com.htp.domain.hibernate;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Collections;
import java.util.Set;

@Setter
@Getter
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "m_features")
public class HibernateFeature {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "name")
	private String name;

	@Column(name = "description")
	private String description;

	@Column(name = "is_deleted")
	private boolean isDeleted;

	@ManyToMany
	@JoinTable(
			name = "l_training_features",
			joinColumns = {@JoinColumn(name = "feature_id")},
			inverseJoinColumns = {@JoinColumn(name = "training_id")}
	)
	@JsonIgnoreProperties("trainings")
	private Set<HibernateTraining> trainings = Collections.emptySet();


}
