//JD19 hibernate
package com.htp.domain.hibernate;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Collections;
import java.util.Set;

@Entity
@Table(name = "m_roles")
//@NoArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
public class HibernateRole {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "role_name")
	private String roleName;

	@Column(name = "is_deleted")
	private boolean isDeleted;

	@ManyToMany
	@JoinTable(
			name = "l_user_roles",
			joinColumns = {@JoinColumn(name = "role_id")},
			inverseJoinColumns = {@JoinColumn(name = "user_id")}
	)
	@JsonIgnoreProperties("roles")
	private Set<HibernateUser> users = Collections.emptySet();

}
