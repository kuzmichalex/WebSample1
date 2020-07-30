package com.htp.domain.hibernate;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;
import java.sql.Date;

@Setter
@Getter
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(exclude = {"userFounder"})
@ToString(exclude = {"userFounder"})
@Entity
@Table(name = "m_groups")
public class HibernateGroup {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "name")
	private String name;

	@Column(name = "description")
	private String description;

	@Column(name = "date_foundation")
	private Date dateFoundation;

	@Column(name = "is_deleted")
	private boolean isDeleted;

	@JsonBackReference
	@ManyToOne
	@JoinColumn(name = "user_founder_id", nullable = false)
	private HibernateUser userFounder;

}
