package com.htp.dao.jdbctemplate;

import com.htp.domain.Activity;
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

@Repository("activityRepositoryJdbcTemplate")
public class ActivityDaoImpl implements ActivityDao {
	private static final String ID = "id";
	private static final String USER_ID = "user_id";
	private static final String GROUP_ID = "group_id";
	private static final String LEVEL_ID = "level_id";
	private static final String TIME_START = "time_start";
	private static final String TIME_END = "time_end";
	private static final String STATE_ID = "state_id";
	private static final String TRAINING_ID = "training_id";
	private static final String IS_DELETED = "is_deleted";

	private final JdbcTemplate jdbcTemplate;
	private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	public ActivityDaoImpl(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
		this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
	}

	private Activity rowMapper(ResultSet resultSet, int i) throws SQLException {
		Activity item = new Activity();
		item.setId(resultSet.getLong(ID));
		item.setUserId(resultSet.getLong(USER_ID));
		item.setGroupId(resultSet.getLong(GROUP_ID));
		item.setLevelId(resultSet.getLong(LEVEL_ID));
		item.setTimeStart(resultSet.getDate(TIME_START));
		item.setTimeEnd(resultSet.getDate(TIME_END));
		item.setStateId(resultSet.getLong(STATE_ID));
		item.setTrainingId(resultSet.getLong(TRAINING_ID));
		item.setDeleted(resultSet.getBoolean(IS_DELETED));
		return item;
	}

	@Override
	public List<Activity> findAll() {
		final String findAllQuery = "select * from m_activity order by id desc";
		return jdbcTemplate.query(findAllQuery, this::rowMapper);
	}

	@Override
	public Optional<Activity> findById(long itemId) {
		return Optional.ofNullable(findOne(itemId));
	}

	@Override
	public Activity findOne(Long itemId) {
		final String searchByIDQuery = "select * from m_activity where id = :id";

		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue(ID, itemId);
		return namedParameterJdbcTemplate.queryForObject(searchByIDQuery, params, this::rowMapper);
	}

	@Override
	public Activity save(Activity item) {
		final String insertQuery = "insert into m_activity (" +
				"user_id," +
				"group_id," +
				"level_id," +
				"time_start," +
				"time_end," +
				"state_id," +
				"training_id," +
				"is_deleted ) values (" +
				":user_id," +
				":group_id," +
				":level_id," +
				":time_start," +
				":time_end," +
				":state_id," +
				":training_id," +
				":is_deleted)";

		MapSqlParameterSource params = new MapSqlParameterSource();
		KeyHolder keyHolder = new GeneratedKeyHolder();

		params.addValue(USER_ID, item.getUserId());
		params.addValue(GROUP_ID, item.getGroupId());
		params.addValue(LEVEL_ID, item.getLevelId());
		params.addValue(TIME_START, item.getTimeStart());
		params.addValue(TIME_END, item.getTimeEnd());
		params.addValue(STATE_ID, item.getStateId());
		params.addValue(TRAINING_ID, item.getTrainingId());
		params.addValue(IS_DELETED, item.isDeleted());

		namedParameterJdbcTemplate.update(insertQuery, params, keyHolder);
		long createdItemId = (Long) Objects.requireNonNull(keyHolder.getKeys()).get(ID);
		return findOne(createdItemId);
	}

	@Override
	public Activity update(Activity item) {
		final String updateQuery = "update m_activity set " +
				"user_id = :user_id," +
				"group_id = :group_id," +
				"level_id = :level_id," +
				"time_start = :time_start," +
				"time_end = :time_end," +
				"state_id = :state_id," +
				"training_id = :training_id," +
				"is_deleted = :is_deleted" +
				" where id = :id";

		MapSqlParameterSource params = new MapSqlParameterSource();
		KeyHolder keyHolder = new GeneratedKeyHolder();
		params.addValue(USER_ID, item.getUserId());
		params.addValue(GROUP_ID, item.getGroupId());
		params.addValue(LEVEL_ID, item.getLevelId());
		params.addValue(TIME_START, item.getTimeStart());
		params.addValue(TIME_END, item.getTimeEnd());
		params.addValue(STATE_ID, item.getStateId());
		params.addValue(TRAINING_ID, item.getTrainingId());
		params.addValue(IS_DELETED, item.isDeleted());
		params.addValue(ID, item.getId());

		namedParameterJdbcTemplate.update(updateQuery, params, keyHolder);
		long updatedItemId = (Long) Objects.requireNonNull(keyHolder.getKeys()).get(ID);
		return findOne(updatedItemId);
	}

	@Override
	public int delete(Activity item) {
		item.setDeleted(true);
		update(item);
		return 1;
	}
}
