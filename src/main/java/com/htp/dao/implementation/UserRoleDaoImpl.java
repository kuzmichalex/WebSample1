package com.htp.dao.implementation;

import com.htp.dao.UserRoleDao;
import com.htp.domain.UserRole;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * class for working with l_userRoles_roles table entries
 */

//Аннотация указывает спрингу, что класс применяется для доступа к базе данных (DAO)
@Repository
public class UserRoleDaoImpl implements UserRoleDao {
	//Наименования колонок в таблице m_userRoles
	public static final String ID = "id";
	public static final String USER_ID = "user_id";
	public static final String ROLE_ID = "role_id";
	private final JdbcTemplate jdbcTemplate;
	private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	public UserRoleDaoImpl(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
		this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
	}

	private UserRole rowMapper(ResultSet resultSet, int i) throws SQLException {
		UserRole item = new UserRole();
		item.setId(resultSet.getLong(ID));
		item.setUserId(resultSet.getLong(USER_ID));
		item.setRoleId(resultSet.getLong(ROLE_ID));
		return item;
	}

	@Override
	public List<UserRole> findAll() {
		final String findAllQuery = "select * from l_user_roles";
		return jdbcTemplate.query(findAllQuery, this::rowMapper);
	}

	@Override
	public Optional<UserRole> findById(long userRolesID) {
		return Optional.ofNullable(findOne(userRolesID));
	}

	@Override
	public UserRole findOne(Long itemId) {
		//search expression
		final String searchByIDQuery = "select * from l_user_roles where id = :id order by id desc";
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue(ID, itemId);
		return namedParameterJdbcTemplate.queryForObject(searchByIDQuery, params, this::rowMapper);

	}

	@Override
	public UserRole save(UserRole item) {
		final String insertQuery = "insert into l_user_roles ( user_id, role_id) values( :user_id, :role_id )";

		MapSqlParameterSource params = new MapSqlParameterSource();
		KeyHolder keyHolder = new GeneratedKeyHolder();

		params.addValue(USER_ID, item.getUserId());
		params.addValue(ROLE_ID, item.getRoleId());

		namedParameterJdbcTemplate.update(insertQuery, params, keyHolder);
		long createdItemId = (Long) Objects.requireNonNull(keyHolder.getKeys()).get(ID);
		return findOne(createdItemId);
	}

	@Override
	public UserRole update(UserRole item) {
		final String updateQuery = "update l_user_roles set user_id = :user_id, role_id = :role_id where id = :id";
		MapSqlParameterSource params = new MapSqlParameterSource();
		KeyHolder keyHolder = new GeneratedKeyHolder();

		params.addValue(USER_ID, item.getUserId());
		params.addValue(ROLE_ID, item.getRoleId());
		params.addValue(ID, item.getId());
		namedParameterJdbcTemplate.update(updateQuery, params, keyHolder);
		long updatedItemId = (Long) Objects.requireNonNull(keyHolder.getKeys()).get(ID);
		return findOne(updatedItemId);
	}

	@Override
	public int delete(UserRole item) {
		final String deleteQuery = "delete from l_user_roles where id=:id";
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue(ID, item.getId());
		return namedParameterJdbcTemplate.update(deleteQuery, params);
	}

}