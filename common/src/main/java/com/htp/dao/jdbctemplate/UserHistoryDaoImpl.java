package com.htp.dao.jdbctemplate;

import com.htp.domain.UserHistory;
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

@Repository("userHistoryRepositoryJdbcTemplate")
public class UserHistoryDaoImpl implements UserHistoryDao {
	public static final String USER_HIST_ID = "id";
	public static final String USER_HIST_USER_ID = "user_id";
	public static final String USER_HIST_DATE = "date";
	public static final String USER_HIST_WEIGHT = "weight";
	public static final String USER_HIST_HEIGHT = "height";
	private final JdbcTemplate jdbcTemplate;
	private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	public UserHistoryDaoImpl(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
		this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
	}

	private UserHistory rowMapper(ResultSet resultSet, int i) throws SQLException {
		UserHistory item = new UserHistory();
		item.setId(resultSet.getLong(USER_HIST_ID));
		item.setUserId(resultSet.getLong(USER_HIST_USER_ID));
		item.setDate(resultSet.getDate(USER_HIST_DATE));
		item.setWeight(resultSet.getInt(USER_HIST_WEIGHT));
		item.setHeight(resultSet.getInt(USER_HIST_HEIGHT));
		return item;
	}

	@Override
	public List<UserHistory> findAll() {
		final String findAllQuery = "select * from m_user_history order by id desc";
		return jdbcTemplate.query(findAllQuery, this::rowMapper);
	}

	@Override
	public Optional<UserHistory> findById(long recordId) {
		return Optional.ofNullable(findOne(recordId));
	}

	@Override
	public UserHistory findOne(Long itemId) {
		final String searchByIDQuery = "select * from m_user_history where id = :id order by id desc";

		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue(USER_HIST_ID, itemId);
		return namedParameterJdbcTemplate.queryForObject(searchByIDQuery, params, this::rowMapper);
	}


	@Override
	public UserHistory save(UserHistory item) {
		final String insertQuery = "insert into m_user_history ( user_id, date, height, weight) " +
				"values( :user_id, :date, :height, :weight)";

		MapSqlParameterSource params = new MapSqlParameterSource();
		KeyHolder keyHolder = new GeneratedKeyHolder();

		params.addValue(USER_HIST_USER_ID, item.getUserId());
		params.addValue(USER_HIST_DATE, item.getDate());
		params.addValue(USER_HIST_HEIGHT, item.getHeight());
		params.addValue(USER_HIST_WEIGHT, item.getWeight());

		namedParameterJdbcTemplate.update(insertQuery, params, keyHolder);
		long createdItemId = (Long) Objects.requireNonNull(keyHolder.getKeys()).get(USER_HIST_ID);
		return findOne(createdItemId);
	}

	@Override
	public UserHistory update(UserHistory item) {
		final String updateQuery = "update m_user_history set user_id = :user_id, date = :date, weight = :weight, height = :height where id = :id";
		MapSqlParameterSource params = new MapSqlParameterSource();
		KeyHolder keyHolder = new GeneratedKeyHolder();

		params.addValue(USER_HIST_USER_ID, item.getUserId());
		params.addValue(USER_HIST_DATE, item.getDate());
		params.addValue(USER_HIST_HEIGHT, item.getHeight());
		params.addValue(USER_HIST_WEIGHT, item.getWeight());
		params.addValue(USER_HIST_ID, item.getId());
		namedParameterJdbcTemplate.update(updateQuery, params, keyHolder);
		long updatedItemId = (Long) Objects.requireNonNull(keyHolder.getKeys()).get(USER_HIST_ID);
		return findOne(updatedItemId);
	}

	@Override
	public int delete(UserHistory item) {
		final String deleteQuery = "delete from m_user_history where id=:id";
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue(USER_HIST_ID, item.getId());
		return namedParameterJdbcTemplate.update(deleteQuery, params);
	}



}
