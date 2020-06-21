package com.htp.dao.implementation;

import com.htp.dao.TrainingFeaturesDao;
import com.htp.domain.TrainingFeatures;
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

@Repository("featureRepositoryJdbcTemplate")
public class TrainingFeaturesDaoImpl implements TrainingFeaturesDao {
	public static final String ID = "id";
	public static final String TRAINING_ID = "training_id";
	public static final String FEATURE_ID = "feature_id";
	public static final String IS_DELETED = "is_deleted";

	private final JdbcTemplate jdbcTemplate;
	private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	public TrainingFeaturesDaoImpl(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
		this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
	}

	private TrainingFeatures rowMapper(ResultSet resultSet, int i) throws SQLException {
		TrainingFeatures item = new TrainingFeatures();
		item.setId(resultSet.getLong(ID));
		item.setId(resultSet.getLong(TRAINING_ID));
		item.setFeatureId(resultSet.getLong(FEATURE_ID));
		item.setDeleted(resultSet.getBoolean(IS_DELETED));
		return item;
	}


	@Override
	public List<TrainingFeatures> findAll() {
		final String findAllQuery = "select * from l_training_features order by id desc";
		return jdbcTemplate.query(findAllQuery, this::rowMapper);
	}

	@Override
	public Optional<TrainingFeatures> findById(long itemId) {
		return Optional.ofNullable(findOne(itemId));
	}

	@Override
	public TrainingFeatures findOne(Long itemId) {
		final String searchByIDQuery = "select * from l_training_features where id = :id";
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue(ID, itemId);
		return namedParameterJdbcTemplate.queryForObject(searchByIDQuery, params, this::rowMapper);
	}

	@Override
	public TrainingFeatures save(TrainingFeatures item) {
		final String insertQuery = "insert into l_training_features (" +
				"training_id," +
				"feature_id," +
				"is_deleted ) values (" +
				":training_id," +
				":feature_id," +
				":is_deleted)";

		MapSqlParameterSource params = new MapSqlParameterSource();
		KeyHolder keyHolder = new GeneratedKeyHolder();
		params.addValue(TRAINING_ID, item.getTrainingId());
		params.addValue(FEATURE_ID, item.getFeatureId());
		params.addValue(IS_DELETED, item.isDeleted());
		namedParameterJdbcTemplate.update(insertQuery, params, keyHolder);
		long createdItemId = (Long) Objects.requireNonNull(keyHolder.getKeys()).get(ID);
		return findOne(createdItemId);
	}

	@Override
	public TrainingFeatures update(TrainingFeatures item) {
		final String updateQuery = "update l_training_features set " +
				"training_id = :training_id," +
				"feature_id = :feature_id," +
				"is_deleted = :is_deleted" +
				" where id = :id";

		MapSqlParameterSource params = new MapSqlParameterSource();
		KeyHolder keyHolder = new GeneratedKeyHolder();
		params.addValue(TRAINING_ID, item.getTrainingId());
		params.addValue(FEATURE_ID, item.getFeatureId());
		params.addValue(IS_DELETED, item.isDeleted());
		params.addValue(ID, item.getId());

		namedParameterJdbcTemplate.update(updateQuery, params, keyHolder);
		long updatedItemId = (Long) Objects.requireNonNull(keyHolder.getKeys()).get(ID);
		return findOne(updatedItemId);
	}

	@Override
	public int delete(TrainingFeatures item) {
		item.setDeleted(true);
		update(item);
		return 1;
	}
}
