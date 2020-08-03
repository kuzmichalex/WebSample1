package com.htp.domain.hibernate;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collections;
import java.util.Set;

@Setter
@Getter
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(exclude = {"activities"})
@ToString(exclude = {"activities"})
@Entity
@Table(name = "m_activity_state")
@Cacheable
public class HibernateTrainingState implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "state_name")
	private String name;

	@Column(name = "is_deleted")
	private boolean isDeleted;

	@JsonManagedReference
	@OneToMany(mappedBy = "stateId", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
	private Set<HibernateActivity> activities = Collections.emptySet();

}
