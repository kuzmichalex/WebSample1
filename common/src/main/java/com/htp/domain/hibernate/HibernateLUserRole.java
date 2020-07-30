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
	private Long id;

	@Column(name = "user_id")
	private Long userId;

	@Column(name = "role_id")
	private Long roleId;

	@Column(name = "is_deleted")
	private boolean isDeleted;
}
