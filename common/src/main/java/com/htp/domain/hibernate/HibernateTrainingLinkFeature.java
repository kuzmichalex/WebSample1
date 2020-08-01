package com.htp.domain.hibernate;


import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@RequiredArgsConstructor
@AllArgsConstructor
//@EqualsAndHashCode(exclude = {
//		 "feature"
//})
//@ToString(exclude = {
//		"feature"
//})
@Entity
@Table(name = "l_training_features")
public class HibernateTrainingLinkFeature {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonBackReference
	@ManyToOne
	@JoinColumn(name = "training_id", nullable = false)
	private HibernateTraining training;

	@JsonBackReference
	@ManyToOne
	@JoinColumn(name = "feature_id", nullable = false)
	private HibernateFeature feature;

	@Column(name = "is_deleted")
	private boolean isDeleted;

}
