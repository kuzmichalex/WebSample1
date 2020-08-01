package com.htp.dao.springdata;

import com.htp.domain.hibernate.HibernateUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;
import java.util.Optional;

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
			" where r.isDeleted = false and ur.isDeleted = false")
	List<HibernateUser> findAllAliveUsers();

	/**
	 * Find user by login
	 *
	 * @param login user login
	 * @return HibernateUser
	 */
	Optional<HibernateUser> findByLoginEquals(String login);

//	Optional<HibernateUser> save(HibernateUser user);

//	select * from m_roles role where role.id in (select role_id from l_user_roles where user_id = :user_id

	//Метод не нужен..  ну может быть пока. Вписан, чтобы не потерялась учебная информация.
	@Query("update HibernateUser u set u.name = :username")
	int updateUserSurname(String username);

}
