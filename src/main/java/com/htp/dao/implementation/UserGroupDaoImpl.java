package com.htp.dao.implementation;

import com.htp.dao.UserGroupDao;
import com.htp.domain.UserGroup;
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

@Repository("userGroupRepositoryJdbcTemplate")
public class UserGroupDaoImpl implements UserGroupDao {
	private static final String ID = "id";
	private static final String USER_ID = "user_id";
	private static final String GROUP_ID = "group_id";
	private static final String DATE_IN = "date_in";
	private static final String DATE_OUT = "date_out";
	private static final String IS_DELETED = "is_deleted";

	private final JdbcTemplate jdbcTemplate;
	private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	public UserGroupDaoImpl(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
		this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
	}
	private UserGroup rowMapper(ResultSet resultSet, int i) throws SQLException {
		UserGroup item = new UserGroup();
		item.setId(resultSet.getLong(ID));
		item.setUserId(resultSet.getLong(USER_ID));
		item.setGroupId(resultSet.getLong(GROUP_ID));
		item.setDateIn(resultSet.getDate(DATE_IN));
		item.setDateOut(resultSet.getDate(DATE_OUT));
		item.setDeleted(resultSet.getBoolean(IS_DELETED));
		return item;
	}
	@Override
	public List<UserGroup> findAll() {
		final String findAllQuery = "select * from l_user_groups order by id desc";
		return jdbcTemplate.query(findAllQuery, this::rowMapper);
	}

	@Override
	public Optional<UserGroup> findById(long itemId) {
		return Optional.ofNullable(findOne(itemId));
	}

	@Override
	public UserGroup findOne(Long itemId) {
		final String searchByIDQuery = "select * from l_user_groups where id = :id";
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue(ID, itemId);
		return namedParameterJdbcTemplate.queryForObject(searchByIDQuery, params, this::rowMapper);
	}

	@Override
	public UserGroup save(UserGroup item) {
		final String insertQuery = "insert into l_user_groups (" +
				"user_id," +
				"group_id," +
				"date_in," +
				"date_out," +
				"is_deleted ) values (" +
				":user_id," +
				":group_id," +
				":date_in," +
				":date_out," +
				":is_deleted)";

		MapSqlParameterSource params = new MapSqlParameterSource();
		KeyHolder keyHolder = new GeneratedKeyHolder();
		params.addValue(USER_ID, item.getUserId());
		params.addValue(GROUP_ID, item.getGroupId());
		params.addValue(DATE_IN, item.getDateIn());
		params.addValue(DATE_OUT, item.getDateOut());
		params.addValue(IS_DELETED, item.isDeleted());
		namedParameterJdbcTemplate.update(insertQuery, params, keyHolder);
		long createdItemId = (Long) Objects.requireNonNull(keyHolder.getKeys()).get(ID);
		return findOne(createdItemId);
	}

	@Override
	public UserGroup update(UserGroup item) {
		final String updateQuery = "update l_user_groups set user_id = :user_id," +
				"group_id = :group_id," +
				"date_in = :date_in," +
				"date_out = :date_out," +
				"is_deleted = :is_deleted" +
				" where id = :id";

		MapSqlParameterSource params = new MapSqlParameterSource();
		KeyHolder keyHolder = new GeneratedKeyHolder();
		params.addValue(GROUP_ID, item.getGroupId());
		params.addValue(DATE_IN, item.getDateIn());
		params.addValue(DATE_OUT, item.getDateOut());
		params.addValue(IS_DELETED, item.isDeleted());
		params.addValue(ID, item.getId());

		namedParameterJdbcTemplate.update(updateQuery, params, keyHolder);
		long updatedItemId = (Long) Objects.requireNonNull(keyHolder.getKeys()).get(ID);
		return findOne(updatedItemId);
	}

	@Override
	public int delete(UserGroup item) {
		item.setDeleted(true);
		update(item);
		return 1;
	}
}
