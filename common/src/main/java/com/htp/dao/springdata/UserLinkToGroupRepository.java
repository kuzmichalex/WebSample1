package com.htp.dao.springdata;

import com.htp.domain.hibernate.HibernateUserLinkGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface UserLinkToGroupRepository extends CrudRepository<HibernateUserLinkGroup, Long>,
		JpaRepository<HibernateUserLinkGroup, Long> {
}
