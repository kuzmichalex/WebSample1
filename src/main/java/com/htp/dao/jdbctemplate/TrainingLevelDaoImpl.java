package com.htp.dao.jdbctemplate;

import com.htp.dao.TrainingLevelDao;
import com.htp.domain.TrainingLevel;
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

@Repository("trainingLevelRepositoryJdbcTemplate")
public class TrainingLevelDaoImpl implements TrainingLevelDao {
	public static final String ID = "id";
	public static final String TRAINING_ID = "training_id";
	public static final String LEVEL_ID = "feature_id";
	public static final String REPETITIONS_MIN = "repetitions_min";
	public static final String REPETITIONS_MAX="repetitions_max";
	public static final String DESCRIPTION="description";
	public static final String TIME_MIN="time_min";
	public static final String TIME_MAX="time_max";
	public static final String IS_DELETED="is_deleted";

	private final JdbcTemplate jdbcTemplate;
	private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	public TrainingLevelDaoImpl(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
		this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
	}

	private TrainingLevel rowMapper(ResultSet resultSet, int i) throws SQLException {
		TrainingLevel item = new TrainingLevel();
		item.setId(resultSet.getLong(ID));
		item.setTrainingId(resultSet.getLong(TRAINING_ID));
		item.setLevelId(resultSet.getLong(LEVEL_ID));
		item.setRepetitionsMin(resultSet.getInt(REPETITIONS_MIN));
		item.setRepetitionsMax(resultSet.getInt(REPETITIONS_MAX));
		item.setDescription(resultSet.getString(DESCRIPTION));
		item.setTimeMin(resultSet.getInt(TIME_MIN));
		item.setTimeMax(resultSet.getInt(TIME_MAX));
		item.setDeleted(resultSet.getBoolean(IS_DELETED));
		return item;
	}

	@Override
	public List<TrainingLevel> findAll() {
		final String findAllQuery = "select * from l_training_levels order by id desc";
		return jdbcTemplate.query(findAllQuery, this::rowMapper);
	}

	@Override
	public Optional<TrainingLevel> findById(long itemId) {
		return Optional.ofNullable(findOne(itemId));
	}

	@Override
	public TrainingLevel findOne(Long itemId) {
		final String searchByIDQuery = "select * from l_training_levels where id = :id";
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue(ID, itemId);
		return namedParameterJdbcTemplate.queryForObject(searchByIDQuery, params, this::rowMapper);
	}

	@Override
	public TrainingLevel save(TrainingLevel item) {
		final String insertQuery = "insert into l_training_levels (" +
				"training_id," +
				"level_id," +
				"repetitions_min," +
				"repetitions_max," +
				"description," +
				"time_min," +
				"time_max," +
				"is_deleted ) values (" +
				":training_id," +
				":level_id," +
				":repetitions_min," +
				":repetitions_max," +
				":description," +
				":time_min," +
				":time_max," +
				":is_deleted)";

		MapSqlParameterSource params = new MapSqlParameterSource();
		KeyHolder keyHolder = new GeneratedKeyHolder();
		params.addValue(TRAINING_ID, item.getTrainingId());
		params.addValue(LEVEL_ID, item.getLevelId());
		params.addValue(REPETITIONS_MIN, item.getRepetitionsMin());
		params.addValue(REPETITIONS_MAX, item.getRepetitionsMax());
		params.addValue(DESCRIPTION, item.getDescription());
		params.addValue(TIME_MIN, item.getTimeMin());
		params.addValue(TIME_MAX, item.getTimeMax());
		params.addValue(IS_DELETED, item.isDeleted());
		namedParameterJdbcTemplate.update(insertQuery, params, keyHolder);
		long createdItemId = (Long) Objects.requireNonNull(keyHolder.getKeys()).get(ID);
		return findOne(createdItemId);
	}

	@Override
	public TrainingLevel update(TrainingLevel item) {
		final String updateQuery = "update l_training_levels set " +
				"training_id = :training_id," +
				"level_id = :level_id," +
				"repetitions_min = :repetitions_min," +
				"repetitions_max = :repetitions_max," +
				"description = :description," +
				"time_min = :time_min," +
				"time_max = :time_max," +
				"is_deleted = :is_deleted" +
				" where id = :id";
		MapSqlParameterSource params = new MapSqlParameterSource();
		KeyHolder keyHolder = new GeneratedKeyHolder();
		params.addValue(TRAINING_ID, item.getTrainingId());
		params.addValue(LEVEL_ID, item.getLevelId());
		params.addValue(REPETITIONS_MIN, item.getRepetitionsMin());
		params.addValue(REPETITIONS_MAX, item.getRepetitionsMax());
		params.addValue(DESCRIPTION, item.getDescription());
		params.addValue(TIME_MIN, item.getTimeMin());
		params.addValue(TIME_MAX, item.getTimeMax());
		params.addValue(IS_DELETED, item.isDeleted());
		params.addValue(ID, item.getId());
		namedParameterJdbcTemplate.update(updateQuery, params, keyHolder);
		long updatedItemId = (Long) Objects.requireNonNull(keyHolder.getKeys()).get(ID);
		return findOne(updatedItemId);
	}

	@Override
	public int delete(TrainingLevel item) {
		item.setDeleted(true);
		update(item);
		return 1;
	}
}
