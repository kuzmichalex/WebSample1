package com.htp.dao.springdata;

import com.htp.domain.hibernate.HibernateRole;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

@CacheConfig(cacheNames = {"roles"})
public interface RoleRepository extends CrudRepository<HibernateRole, Long>,
		JpaRepository<HibernateRole, Long>,
		PagingAndSortingRepository<HibernateRole, Long> {

	@Override
	Optional<HibernateRole> findById(Long aLong);

	Optional<HibernateRole> findByRoleName(String roleName);

//	@Modifying
//	@Query(value = "insert into m_roles ( role_name ) values (:role_name)" , nativeQuery = true)
//	void save(HibernateRole role);

}
