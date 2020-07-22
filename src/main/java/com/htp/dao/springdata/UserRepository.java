package com.htp.dao.springdata;

import com.htp.domain.hibernate.HibernateUser;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

@CacheConfig(cacheNames = {"usersAdmins"})
public interface UserRepository extends CrudRepository<HibernateUser, Long>,
		JpaRepository<HibernateUser, Long>,
		PagingAndSortingRepository<HibernateUser, Long> {

	/**
	 * Finding all users except deleted
	 */
	@Query(value = "select * from m_users u inner join l_user_roles ur on u.id = ur.user_id join m_roles r on ur.role_id" +
			" where r.is_deleted=false and ur.is_deleted=false", nativeQuery = true)
	List<HibernateUser> findAllAliveUsersNative();

	/**
	 * Finding all users except deleted
	 */
	@Query(value = "select u from HibernateUser u inner join HibernateLUserRole ur on u.id = ur.userId " +
			" inner join HibernateRole r on r.id = ur.roleId " +
			" where r.isDeleted = false and ur.isDeleted = false", nativeQuery = false)
	List<HibernateUser> findAllAliveUsers();


	/**
	 * Find user by login
	 *
	 * @param login user login
	 * @return HibernateUser
	 */
	HibernateUser findByLoginEquals(String login);

	//Метод не нужен..  ну может быть пока. Вписан, чтобы не потерялась учебная информация.
	@Modifying
	@Query("update HibernateUser u set u.name = :username")
	int updateUserSurname(String username);

}
