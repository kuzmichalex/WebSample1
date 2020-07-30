package com.htp.domain.hibernate;

import lombok.*;

import javax.persistence.*;

@Setter
@Getter
@RequiredArgsConstructor
@AllArgsConstructor
@Data
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

	@Column(name = "author_user_id")
	private String authorUserId;
}
