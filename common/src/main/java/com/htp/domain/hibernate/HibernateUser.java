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
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.sql.Date;
import java.util.Collections;
import java.util.Set;

@Setter
@Getter
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Table(name = "m_users")
public class HibernateUser {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

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

}
