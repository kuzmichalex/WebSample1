package com.htp.dao.springdata;

import com.htp.domain.hibernate.HibernateGroup;
import com.htp.domain.hibernate.HibernateUser;
import com.htp.domain.hibernate.HibernateUserLinkGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface GroupRepository extends CrudRepository<HibernateGroup, Long>,
		JpaRepository<HibernateGroup, Long>,
		PagingAndSortingRepository<HibernateGroup, Long> {

	List<HibernateGroup> findAll();

	/* find all user's group except deleted */
	@Query(value = "select gr from HibernateGroup gr join HibernateUserLinkGroup lnk on lnk.group = gr.id" +
			" where lnk.user = :user and gr.isDeleted = false and lnk.isDeleted = false")
	List<HibernateGroup> findAllUserGroups(@Param("user") Long userId);

	@Query(value = "select lnk from HibernateUserLinkGroup lnk" +
			" where lnk.user = :user and lnk.group = :group")
	Optional<HibernateUserLinkGroup> getUserLinkToGroup(@Param("user") Long userId, @Param("group") Long groupId);

	HibernateUser save(HibernateUser user);

}


