package com.htp.dao.jdbctemplate;

import com.htp.dao.ActivityStateDao;
import com.htp.domain.ActivityState;
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

@Repository("activityStateRepositoryJdbcTemplate")
public class ActivityStateDaoImpl implements ActivityStateDao {

	private static final String ID ="id";
	private static final String STATE_NAME ="state_name";
	private static final String IS_DELETED ="is_deleted";

	private final JdbcTemplate jdbcTemplate;
	private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	public ActivityStateDaoImpl(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
		this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
	}

	private ActivityState rowMapper(ResultSet resultSet, int i) throws SQLException {
		ActivityState item = new ActivityState();

		item.setId(resultSet.getLong(ID));
		item.setStateName(resultSet.getLong(STATE_NAME));
		item.setDeleted(resultSet.getBoolean(IS_DELETED));
		return item;
	}

	@Override
	public List<ActivityState> findAll() {
		final String findAllQuery = "select * from m_activity_state order by id desc";
		return jdbcTemplate.query(findAllQuery, this::rowMapper);
	}

	@Override
	public Optional<ActivityState> findById(long itemId) {
		return Optional.ofNullable(findOne(itemId));
	}

	@Override
	public ActivityState findOne(Long itemId) {
		final String searchByIDQuery = "select * from m_activity_state where id = :id";

		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue(ID, itemId);
		return namedParameterJdbcTemplate.queryForObject(searchByIDQuery, params, this::rowMapper);
	}

	@Override
	public ActivityState save(ActivityState item) {
		final String insertQuery = "insert into m_activity_state (" +
				"state_name," +
				"is_deleted) values (" +
				":state_name," +
				":is_deleted)";

		MapSqlParameterSource params = new MapSqlParameterSource();
		KeyHolder keyHolder = new GeneratedKeyHolder();
		params.addValue(STATE_NAME, item.getStateName());
		params.addValue(IS_DELETED, item.isDeleted());
		namedParameterJdbcTemplate.update(insertQuery, params, keyHolder);
		long createdItemId = (Long) Objects.requireNonNull(keyHolder.getKeys()).get(ID);
		return findOne(createdItemId);
	}

	@Override
	public ActivityState update(ActivityState item) {
		final String updateQuery = "update m_activity_state set " +
				"state_name = :state_name," +
				"is_deleted = :is_deleted" +
				" where id = :id";

		MapSqlParameterSource params = new MapSqlParameterSource();
		KeyHolder keyHolder = new GeneratedKeyHolder();
		params.addValue(STATE_NAME, item.getStateName());
		params.addValue(IS_DELETED, item.isDeleted());
		params.addValue(ID, item.getId());
		namedParameterJdbcTemplate.update(updateQuery, params, keyHolder);
		long updatedItemId = (Long) Objects.requireNonNull(keyHolder.getKeys()).get(ID);
		return findOne(updatedItemId);
	}

	@Override
	public int delete(ActivityState item) {
		item.setDeleted(true);
		update(item);
		return 1;
	}
}
