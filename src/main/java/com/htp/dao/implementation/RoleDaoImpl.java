package com.htp.dao.implementation;

import com.htp.dao.RoleDao;
import com.htp.domain.Role;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

//Аннотация указывает спрингу, что класс применяется для доступа к базе данных (DAO)
@Repository("roleRepositoryJdbcTemplate")
public class RoleDaoImpl implements RoleDao {
	//Наименования колонок в таблице m_roles
	public static final String ROLE_ID = "id";
	public static final String ROLE_NAME = "role_name";
	
	private final JdbcTemplate jdbcTemplate;
	private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	public RoleDaoImpl(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
		this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
	}

	private Role rowMapper(ResultSet resultSet, int i) throws SQLException {
		Role user = new Role();
		user.setId(resultSet.getLong(ROLE_ID));
		user.setRoleName(resultSet.getString(ROLE_NAME));
		return user;
	}	

	@Override
	public List<Role> findAll() {
		final String findAllQuery = "select * from m_roles order by id desc";
		return jdbcTemplate.query(findAllQuery, this::rowMapper);
	}

	@Override
	public Optional<Role> findById(long roleID) {
		return Optional.ofNullable(findOne(roleID));
	}


	@Override
	public Role findOne(Long itemId) {
		//search expression
		final String searchByIDQuery = "select * from m_roles where id = :id";

		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue(ROLE_ID, itemId);
		return namedParameterJdbcTemplate.queryForObject(searchByIDQuery, params, this::rowMapper);
	}

	@Override
	public Optional<Role> findByRoleName(String roleName) {
		final String searchByIDQuery = "select * from m_roles where role_name = :role_name";
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue(ROLE_NAME, roleName);
		try {
			return Optional.ofNullable(namedParameterJdbcTemplate.queryForObject(searchByIDQuery, params, this::rowMapper));
		}catch(EmptyResultDataAccessException e){
			return Optional.empty();
		}
	}

	@Override
	public Role save(Role item) {
		final String insertQuery = "insert into m_roles ( role_name ) values (:role_name)";

		MapSqlParameterSource params = new MapSqlParameterSource();
		KeyHolder keyHolder = new GeneratedKeyHolder();

		params.addValue(ROLE_NAME, item.getRoleName());
		namedParameterJdbcTemplate.update(insertQuery, params, keyHolder);
		long createdItemId = (Long) Objects.requireNonNull(keyHolder.getKeys()).get(ROLE_ID);
		return findOne(createdItemId);
	}

	@Override
	public Role update(Role item) {
		final String updateQuery = "update m_roles set role_name = :role_name where id = :id";
		MapSqlParameterSource params = new MapSqlParameterSource();
		KeyHolder keyHolder = new GeneratedKeyHolder();
		params.addValue(ROLE_NAME, item.getRoleName());
		params.addValue(ROLE_ID, item.getId());
		namedParameterJdbcTemplate.update(updateQuery, params, keyHolder);
		long updatedUserId = (Long) Objects.requireNonNull(keyHolder.getKeys()).get(ROLE_ID);
		return findOne(updatedUserId);
	}

	@Override
	public int delete(Role item) {
		final String deleteQuery = "delete from m_roles where id=:id";
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue(ROLE_ID, item.getId());
		return namedParameterJdbcTemplate.update(deleteQuery, params);
	}
}
