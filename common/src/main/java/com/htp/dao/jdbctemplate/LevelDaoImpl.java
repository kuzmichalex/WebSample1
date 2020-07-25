package com.htp.dao.jdbctemplate;

import com.htp.domain.Level;
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

@Repository("levelRepositoryJdbcTemplate")
public class LevelDaoImpl implements LevelDao {
	//Column names
	private static final String ID = "id";
	private static final String NAME = "name";
	private static final String IS_DELETED = "is_deleted";

	private final JdbcTemplate jdbcTemplate;
	private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	public LevelDaoImpl(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
		this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
	}

	private Level rowMapper(ResultSet resultSet, int i) throws SQLException {
		Level item = new Level();
		item.setId(resultSet.getLong(ID));
		item.setName(resultSet.getString(NAME));
		item.setDeleted(resultSet.getBoolean(IS_DELETED));
		return item;
	}

	@Override
	public List<Level> findAll() {
		final String findAllQuery = "select * from m_levels order by id desc";
		return jdbcTemplate.query(findAllQuery, this::rowMapper);
	}

	@Override
	public Optional<Level> findById(long itemId)    {
		return Optional.ofNullable(findOne(itemId));
	}

	@Override
	public Level findOne(Long itemId) {
		final String searchByIDQuery = "select * from m_levels where id = :id";
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue(ID, itemId);
		return namedParameterJdbcTemplate.queryForObject(searchByIDQuery, params, this::rowMapper);
	}

	@Override
	public Level save(Level item) {
		final String insertQuery = "insert into m_levels ( name, is_deleted) values (:name, :is_deleted)";

		MapSqlParameterSource params = new MapSqlParameterSource();
		KeyHolder keyHolder = new GeneratedKeyHolder();
		params.addValue(NAME, item.getName());
		params.addValue(IS_DELETED, item.isDeleted());
		namedParameterJdbcTemplate.update(insertQuery, params, keyHolder);
		long createdItemId = (Long) Objects.requireNonNull(keyHolder.getKeys()).get(ID);
		return findOne(createdItemId);
	}

	@Override
	public Level update(Level item) {
		final String updateQuery = "update m_levels set name = :name" +
				" where id = :id";

		MapSqlParameterSource params = new MapSqlParameterSource();
		KeyHolder keyHolder = new GeneratedKeyHolder();
		params.addValue(NAME, item.getName());
		params.addValue(ID, item.getId());
		namedParameterJdbcTemplate.update(updateQuery, params, keyHolder);
		long updatedItemId = (Long) Objects.requireNonNull(keyHolder.getKeys()).get(ID);
		return findOne(updatedItemId);
	}

	@Override
	public int delete(Level item) {
		item.setDeleted(true);
		update(item);
		return 1;
	}
}

