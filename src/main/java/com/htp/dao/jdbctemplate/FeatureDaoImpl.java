package com.htp.dao.jdbctemplate;

import com.htp.dao.FeatureDao;
import com.htp.domain.Feature;
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
public class FeatureDaoImpl implements FeatureDao {
	private static final String ID="id";
	private static final String DESCRIPTION="description";
	private static final String NAME="name";
	private static final String IS_DELETED="is_deleted";

	private final JdbcTemplate jdbcTemplate;
	private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	public FeatureDaoImpl(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
		this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
	}
	private Feature rowMapper(ResultSet resultSet, int i) throws SQLException {
		Feature item = new Feature();
		item.setId(resultSet.getLong(ID));
		item.setDescription(resultSet.getString(DESCRIPTION));
		item.setName(resultSet.getString(NAME));
		item.setDeleted(resultSet.getBoolean(IS_DELETED));
		return item;
	}

	@Override
	public List<Feature> findAll() {
		final String findAllQuery = "select * from m_features order by id desc";
		return jdbcTemplate.query(findAllQuery, this::rowMapper);
	}

	@Override
	public Optional<Feature> findById(long itemId) {
		return Optional.ofNullable(findOne(itemId));
	}

	@Override
	public Feature findOne(Long itemId) {
		final String searchByIDQuery = "select * from m_features where id = :id";
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue(ID, itemId);
		return namedParameterJdbcTemplate.queryForObject(searchByIDQuery, params, this::rowMapper);
	}

	@Override
	public Feature save(Feature item) {
		final String insertQuery = "insert into m_features (" +
				"description," +
				"name," +
				"is_deleted ) values (" +
				":description," +
				":name," +
				":is_deleted)";

		MapSqlParameterSource params = new MapSqlParameterSource();
		KeyHolder keyHolder = new GeneratedKeyHolder();
		params.addValue(DESCRIPTION, item.getDescription());
		params.addValue(NAME, item.getName());
		params.addValue(IS_DELETED, item.isDeleted());
		namedParameterJdbcTemplate.update(insertQuery, params, keyHolder);
		long createdItemId = (Long) Objects.requireNonNull(keyHolder.getKeys()).get(ID);
		return findOne(createdItemId);
	}

	@Override
	public Feature update(Feature item) {
		final String updateQuery = "update m_features set name = :name," +
				"description = :description," +
				"is_deleted = :is_deleted" +
				" where id = :id";

		MapSqlParameterSource params = new MapSqlParameterSource();
		KeyHolder keyHolder = new GeneratedKeyHolder();
		params.addValue(NAME, item.getName());
		params.addValue(DESCRIPTION, item.getDescription());
		params.addValue(IS_DELETED, item.isDeleted());
		params.addValue(ID, item.getId());

		namedParameterJdbcTemplate.update(updateQuery, params, keyHolder);
		long updatedItemId = (Long) Objects.requireNonNull(keyHolder.getKeys()).get(ID);
		return findOne(updatedItemId);
	}

	@Override
	public int delete(Feature item) {
		item.setDeleted(true);
		update(item);
		return 1;
	}
}
