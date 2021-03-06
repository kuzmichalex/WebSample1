package com.htp.domain.hibernate;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.util.Collections;
import java.util.Set;

@Setter
@Getter
@RequiredArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(exclude = {"features"})
@ToString(exclude = {"features"})

@Entity
@Table(name = "m_trainings")
public class HibernateTraining {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "name")
	private String name;

	@Column(name = "description")
	private String description;

	@Column(name = "is_deleted")
	private boolean isDeleted;

	@JsonBackReference
	@ManyToOne
	@JoinColumn(name = "author_user_id", nullable = false)
	private HibernateUser userAuthor;

	@ManyToMany(mappedBy = "trainings", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JsonIgnoreProperties("trainings")
	private Set<HibernateFeature> features = Collections.emptySet();

}
