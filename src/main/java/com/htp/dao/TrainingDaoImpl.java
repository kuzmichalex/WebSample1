package com.htp.dao;

import com.htp.domain.Training;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Repository
public class TrainingDaoImpl implements TrainingDao {
	public static final String TRAINING_ID = "id";
	public static final String TRAINING_NAME = "name";
	public static final String TRAINING_DESCRIPTION = "description";
	public static final String TRAINING_AUTHOR_USER_ID = "author_user_id";

	private JdbcTemplate jdbcTemplate;
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	public TrainingDaoImpl(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
		this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
	}

	private Training trainingRowMapper(ResultSet resultSet, int i) throws SQLException {
		Training training = new Training();
		training.setId(resultSet.getLong(TRAINING_ID));
		training.setName(resultSet.getString(TRAINING_NAME));
		training.setDescription(resultSet.getString(TRAINING_DESCRIPTION));
		training.setId(resultSet.getLong(TRAINING_AUTHOR_USER_ID));
		return training;
	}

	@Override
	public List<Training> findAll() {
		final String findAllQuery = "select * from m_trainings order by id desc";
		return jdbcTemplate.query(findAllQuery, this::trainingRowMapper);
	}

	@Override
	public List<Training> search(String paramSearch) {
		return null;
	}

	@Override
	public Optional<Training> findById(long userID) {
		return Optional.empty();
	}

	@Override
	public Training findOne(long userID) {
		return null;
	}

	@Override
	public Training save(Training training) {
		final String saveQuery = "insert into m_trainings ( name, description, author_user_id) values( ?, ?, ?)";
		List<Training> query = jdbcTemplate.query(saveQuery, this::trainingRowMapper);
		//jdbcTemplate.update(saveQuery, training.getName(), training.getDescription(), training.getUserAuthorId());
		return query.get(0);
	}

	@Override
	public Training update(Training training) {
		return null;
	}

	@Override
	public int Delete(Training training) {
		return 0;
	}

	@Override
	public int insertBatch(List<Training> trainings) {
		return 0;
	}
}
