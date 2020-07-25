package com.htp.dao.jdbctemplate;

import com.htp.dao.TrainingDao;
import com.htp.domain.Training;
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

@Repository("TrainingRepositoryJdbcTemplate")
public class TrainingDaoImpl implements TrainingDao {
	public static final String TRAINING_ID = "id";
	public static final String TRAINING_NAME = "name";
	public static final String TRAINING_DESCRIPTION = "description";
	public static final String TRAINING_AUTHOR_USER_ID = "author_user_id";

	private final JdbcTemplate jdbcTemplate;
	private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	public TrainingDaoImpl(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
		this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
	}

	private Training trainingRowMapper(ResultSet resultSet, int i) throws SQLException {
		Training training = new Training();
		training.setId(resultSet.getLong(TRAINING_ID));
		training.setName(resultSet.getString(TRAINING_NAME));
		training.setDescription(resultSet.getString(TRAINING_DESCRIPTION));
		training.setUserAuthorId(resultSet.getLong(TRAINING_AUTHOR_USER_ID));
		return training;
	}


	@Override
	public List<Training> findAll() {
		final String findAllQuery = "select * from m_trainings order by id desc";
		return jdbcTemplate.query(findAllQuery, this::trainingRowMapper);

	}

	@Override
	public Optional<Training> findById(long itemId) {
		return Optional.ofNullable(findOne(itemId));
	}

	@Override
	public Training findOne(Long itemId) {
		final String findById = "select * from m_trainings where id = :id";
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue(TRAINING_ID, itemId);
		return namedParameterJdbcTemplate.queryForObject(findById, params, this::trainingRowMapper);
	}

	@Override
	public Training save(Training item) {
		final String insertQuery = "insert into m_trainings ( name, description, author_user_id) values( :name, :description, :author_user_id)";

		MapSqlParameterSource params = new MapSqlParameterSource();
		KeyHolder keyHolder = new GeneratedKeyHolder();

		params.addValue(TRAINING_NAME, item.getName());
		params.addValue(TRAINING_DESCRIPTION, item.getDescription());
		params.addValue(TRAINING_AUTHOR_USER_ID, item.getUserAuthorId());
		namedParameterJdbcTemplate.update(insertQuery, params, keyHolder);
		long createdItemId = (Long) Objects.requireNonNull(keyHolder.getKeys()).get(TRAINING_ID);
		return findOne(createdItemId);
	}

	@Override
	public Training update(Training item) {
		final String updateQuery = "update m_trainings set name = :name, description = :description, author_user_id = :author_user_id where id = :id";
		MapSqlParameterSource params = new MapSqlParameterSource();
		KeyHolder keyHolder = new GeneratedKeyHolder();
		params.addValue(TRAINING_NAME, item.getName());
		params.addValue(TRAINING_DESCRIPTION, item.getDescription());
		params.addValue(TRAINING_AUTHOR_USER_ID, item.getUserAuthorId());
		params.addValue(TRAINING_ID, item.getId());
		namedParameterJdbcTemplate.update(updateQuery, params, keyHolder);
		long updatedItemId = (Long) Objects.requireNonNull(keyHolder.getKeys()).get(TRAINING_ID);
		return findOne(updatedItemId);
	}

	@Override
	public int delete(Training item) {
		final String deleteQuery = "delete from m_trainings where id = :id";
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue(TRAINING_ID, item.getId());
		return namedParameterJdbcTemplate.update(deleteQuery, params);
	}

}
