package com.htp.dao.implementation;

import com.htp.dao.UserDao;
import com.htp.domain.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * class for working with l_user_roles table entries
 */

//Аннотация указывает спрингу, что класс применяется для доступа к базе данных (DAO)
@Repository
public class UserDaoImpl implements UserDao {
	//Наименования колонок в таблице m_user
	public static final String USER_ID = "id";
	public static final String USER_LOGIN = "login";
	public static final String USER_NAME = "name";
	public static final String USER_BIRTHDAY = "birth_date";
	public static final String USER_PASSWORD = "password";

	private final JdbcTemplate jdbcTemplate;
	private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;


	public UserDaoImpl(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
		this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
	}

	private User rowMapper(ResultSet resultSet, int i) throws SQLException {
		User user = new User();
		user.setId(resultSet.getLong(USER_ID));
		user.setName(resultSet.getString(USER_NAME));
		user.setLogin(resultSet.getString(USER_LOGIN));
		user.setBirthDate(resultSet.getDate(USER_BIRTHDAY));
		user.setPassword(resultSet.getString(USER_PASSWORD));
		return user;
	}


	@Override
	public List<User> findAll() {
		final String findAllQuery = "select * from m_users order by id desc";
		return jdbcTemplate.query(findAllQuery, this::rowMapper);
	}

	@Override
	public List<User> search(long itemId) {
		final String searchQuery = "select * from m_users where id > :id";
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue(USER_ID, itemId);
		return namedParameterJdbcTemplate.query(searchQuery, params, this::rowMapper);
	}

	@Override
	public Optional<User> findById(long itemId) {
		return Optional.ofNullable(findOne(itemId));
	}

	@Override
	public User findOne(Long itemId) {
		//search expression
		final String searchByIdQuery = "select * from m_users where id = :id";
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue(USER_ID, itemId);
		return namedParameterJdbcTemplate.queryForObject(searchByIdQuery, params, this::rowMapper);
	}

	@Override
	public User save(User item) {
		final String insertQuery = "insert into m_users ( login, name, password, birth_date) values( :login, :name, :password, :birth_date)";

		MapSqlParameterSource params = new MapSqlParameterSource();
		KeyHolder keyHolder = new GeneratedKeyHolder();

		params.addValue(USER_NAME, item.getName());
		params.addValue(USER_LOGIN, item.getLogin());
		params.addValue(USER_PASSWORD, item.getPassword());
		params.addValue(USER_BIRTHDAY, item.getBirthDate());

		namedParameterJdbcTemplate.update(insertQuery, params, keyHolder);
		long createdItemId = (Long) Objects.requireNonNull(keyHolder.getKeys()).get(USER_ID);
		return findOne(createdItemId);

	}

	@Override
	public User update(User item) {
		final String updateQuery = "update m_users set login = :login, name = :name,  password = :password, birth_date = :birth_date where id = :id";
		MapSqlParameterSource params = new MapSqlParameterSource();
		KeyHolder keyHolder = new GeneratedKeyHolder();
		params.addValue(USER_NAME, item.getName());
		params.addValue(USER_LOGIN, item.getLogin());
		params.addValue(USER_PASSWORD, item.getPassword());
		params.addValue(USER_BIRTHDAY, item.getBirthDate());
		params.addValue(USER_ID, item.getId());
		namedParameterJdbcTemplate.update(updateQuery, params, keyHolder);
		long updatedUserId = (Long) Objects.requireNonNull(keyHolder.getKeys()).get(USER_ID);
		return findOne(updatedUserId);
	}

	@Override
	public int delete(User item) {
		final String deleteQuery = "delete from m_users where id = :id";
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue(USER_ID, item.getId());
		return namedParameterJdbcTemplate.update(deleteQuery, params);
	}


	/**
	 * batch insert
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public int insertBatch(List<User> users) {
		String saveQuery = "insert into m_users ( login, name, birth_date, password) values( :login, :name, :birth_date, :password)";
		List<SqlParameterSource> batchParamList = new ArrayList<>();
		for (User user : users) {
			MapSqlParameterSource params = new MapSqlParameterSource();
			params.addValue(USER_LOGIN, user.getLogin());
			params.addValue(USER_NAME, user.getName());
			params.addValue(USER_BIRTHDAY, user.getBirthDate());
			params.addValue(USER_PASSWORD, user.getPassword());
			batchParamList.add(params);
		}
		int size = batchParamList.size();
		namedParameterJdbcTemplate.batchUpdate(saveQuery, batchParamList.toArray(new SqlParameterSource[size]));
		return size;
	}

}
