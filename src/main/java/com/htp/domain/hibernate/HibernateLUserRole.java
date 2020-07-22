package com.htp.domain.hibernate;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "l_user_roles")
@RequiredArgsConstructor
@Getter
@Setter
public class HibernateLUserRole {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "user_id")
	private long userId;

	@Column(name = "role_id")
	private long roleId;

	@Column(name = "is_deleted")
	private boolean isDeleted;
}
