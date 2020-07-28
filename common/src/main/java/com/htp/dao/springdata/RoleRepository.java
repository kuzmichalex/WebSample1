package com.htp.dao.springdata;

import com.htp.domain.hibernate.HibernateRole;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

@CacheConfig(cacheNames = {"RolesCache"})
public interface RoleRepository extends CrudRepository<HibernateRole, Long>,
		JpaRepository<HibernateRole, Long>,
		PagingAndSortingRepository<HibernateRole, Long> {

	@Override
	@Cacheable
	Optional<HibernateRole> findById(Long aLong);

	@Cacheable
	Optional<HibernateRole> findByRoleName(String roleName);

//	select * from m_roles role where role.id in " +
//			"(select role_id from l_user_roles where user_id = :user_id )
//	@Query(value = "select * from m_roles role where role.id in " +
//			"(select role_id from l_user_roles where user_id = :user_id )")
//	)
//	Optional<HibernateRole> findRolesByUsers(HibernateUser user);


//	@Modifying
//	@Query(value = "insert into m_roles ( role_name ) values (:role_name)" , nativeQuery = true)
//	void save(HibernateRole role);

}
