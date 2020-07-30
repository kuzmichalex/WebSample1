package com.htp.domain.hibernate;


import lombok.*;

import javax.persistence.*;
import java.sql.Date;

@Setter
@Getter
@RequiredArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@Entity
@Table(name = "l_user_groups")
@Cacheable
public class HibernateUserLinkGroup {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "user_id")
	private Long user;

	@Column(name = "group_id")
	private Long group;

	@Column(name = "date_in")
	private Date dateIn;

	@Column(name = "date_out")
	private Date dateOut;

	@Column(name = "is_deleted")
	private boolean isDeleted;
}
