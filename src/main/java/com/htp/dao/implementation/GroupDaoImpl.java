package com.htp.dao.implementation;

import com.htp.dao.GroupDao;
import com.htp.domain.Group;
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


@Repository("groupRepositoryJdbcTemplate")
public class GroupDaoImpl implements GroupDao {
	//Column names
	private static final String ID = "id";
	private static final String NAME = "name";
	private static final String DESCRIPTION = "description";
	private static final String USER_FOUNDER_ID = "user_founder_id";
	private static final String DATE_FOUNDATION = "date_foundation";
	private static final String IS_DELETED = "is_deleted";

	private final JdbcTemplate jdbcTemplate;
	private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	public GroupDaoImpl(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
		this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
	}

	private Group rowMapper(ResultSet resultSet, int i) throws SQLException {
		Group item = new Group();
		item.setId(resultSet.getLong(ID));
		item.setName(resultSet.getString(NAME));
		item.setDescription(resultSet.getString(DESCRIPTION));
		item.setUserFounderId(resultSet.getLong(USER_FOUNDER_ID));
		item.setDateFoundation(resultSet.getDate(DATE_FOUNDATION));
		item.setDeleted(resultSet.getBoolean(IS_DELETED));
		return item;
	}

	@Override
	public List<Group> findAll() {
		final String findAllQuery = "select * from m_groups order by id desc";
		return jdbcTemplate.query(findAllQuery, this::rowMapper);
	}

	@Override
	public Optional<Group> findById(long itemId) {
		return Optional.ofNullable(findOne(itemId));
	}

	@Override
	public Group findOne(Long itemId) {
		final String searchByIDQuery = "select * from m_groups where id = :id";

		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue(ID, itemId);
		return namedParameterJdbcTemplate.queryForObject(searchByIDQuery, params, this::rowMapper);
	}

	@Override
	public Group save(Group item) {
		final String insertQuery = "insert into m_groups ( name, description, user_founder_id, date_foundation ) values (:name, :description, :user_founder_id, :date_foundation)";

		MapSqlParameterSource params = new MapSqlParameterSource();
		KeyHolder keyHolder = new GeneratedKeyHolder();

		params.addValue(NAME, item.getName());
		params.addValue(DESCRIPTION, item.getDescription());
		params.addValue(USER_FOUNDER_ID, item.getUserFounderId());
		params.addValue(DATE_FOUNDATION, item.getDateFoundation());

		namedParameterJdbcTemplate.update(insertQuery, params, keyHolder);
		long createdItemId = (Long) Objects.requireNonNull(keyHolder.getKeys()).get(ID);
		return findOne(createdItemId);
	}

	@Override
	public Group update(Group item) {
		final String updateQuery = "update m_groups set name = :name," +
				"description = :description," +
				"user_founder_id = :user_founder_id," +
				"date_foundation = :date_foundation," +
				"is_deleted = :is_deleted" +
				" where id = :id";

		MapSqlParameterSource params = new MapSqlParameterSource();
		KeyHolder keyHolder = new GeneratedKeyHolder();
		params.addValue(NAME, item.getName());
		params.addValue(DESCRIPTION, item.getDescription());
		params.addValue(USER_FOUNDER_ID, item.getUserFounderId());
		params.addValue(DATE_FOUNDATION, item.getDateFoundation());
		params.addValue(IS_DELETED, item.isDeleted());
		params.addValue(ID, item.getId());

		namedParameterJdbcTemplate.update(updateQuery, params, keyHolder);
		long updatedItemId = (Long) Objects.requireNonNull(keyHolder.getKeys()).get(ID);
		return findOne(updatedItemId);
	}

	@Override
	public int delete(Group item) {
		item.setDeleted(true);
		update(item);
		return 1;
	}
}
