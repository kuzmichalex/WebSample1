package com.htp.dao.springdata;

import com.htp.domain.hibernate.HibernateUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

//@CacheConfig(cacheNames = {"usersAdmins"})
public interface UserRepository extends CrudRepository<HibernateUser, Long>,
		JpaRepository<HibernateUser, Long>,
		PagingAndSortingRepository<HibernateUser, Long> {

//	@Cacheable
//	@Query(value = "select u from HibernateUser u")
//	List<HibernateUser> findAll2();

	HibernateUser findByLoginEquals(String login);

	//Метод не нужен..  ну может быть пока. Вписан, чтобы не потерялась учебная информация.
	@Modifying
	@Query("update HibernateUser u set u.name = :username")
	int updateUserSurname(String username);

}
