//JD18 hibernate

package com.htp.domain.hibernate;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;
import java.util.Collections;
import java.util.Set;

@Setter
@Getter
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "m_users")
public class HibernateUser {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column
	private String login;

	@Column
	private String name;

	@Column(name ="birth_date")
	private Date birthDate;

	@Column
	private String password;

	@ManyToMany(mappedBy = "users")
	@JsonIgnoreProperties("users")
	private Set<HibernateRole> roles = Collections.emptySet();

}
