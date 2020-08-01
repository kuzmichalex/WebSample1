//JD18 hibernate
/*
@Entity указывает, что класс является сущностью
@Table указывает имя таблицы, которую отображает сущность
@Id Так мы указываем колонку с первичным ключём
@Column указывает обычную колонку модификатор name указывает имя колонки в базе данных
@GeneratedValue указывает стратегию заполения поля
*/


package com.htp.domain.hibernate;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.util.Collections;
import java.util.Set;


@Setter
@Getter
@RequiredArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(exclude = {"roles", "createdGroups", "createdTrainings"})
@ToString(exclude = {"roles", "createdGroups", "createdTrainings"})
@Entity
@Table(name = "m_users")
@Cacheable
public class HibernateUser implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column
	private String login;

	@Column
	private String name;

	@Column(name = "birth_date")
	private Date birthDate;

	@Column
	private String password;

	@ManyToMany(mappedBy = "users", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JsonIgnoreProperties("users")
	private Set<HibernateRole> roles = Collections.emptySet();

	@JsonManagedReference
	@OneToMany(mappedBy = "userFounder", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
	private Set<HibernateGroup> createdGroups = Collections.emptySet();

	@JsonManagedReference
	@OneToMany(mappedBy = "userAuthor", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
	private Set<HibernateTraining> createdTrainings = Collections.emptySet();


}
