package com.htp.dao;

import com.htp.domain.User;
import com.htp.domain.UserHistory;
import com.htp.exceptions.ResourceNotFoundException;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class UserHistoryDaoImpl implements UserHistoryDao {
	private DataSource dataSource;
	public static final String USER_HIST_ID = "id";
	public static final String USER_HIST_USER_ID = "user_id";
	public static final String USER_HIST_DATE = "date";
	public static final String USER_HIST_WEIGHT = "weight";
	public static final String USER_HIST_HEIGHT = "height";

	public UserHistoryDaoImpl(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	@Override
	public List<UserHistory> findAll() {
		final String findAllQuery = "select * from m_user_history";
		List<UserHistory> listUserHistory = new ArrayList<>();
		ResultSet resultSet = null;

		try (Connection connection = dataSource.getConnection();
		     //3. Готовим выражение
		     PreparedStatement preparedStatement = connection.prepareStatement(findAllQuery)
		) {
			//4. Выполняем выражение
			resultSet = preparedStatement.executeQuery();
			//5. Вычитываем результат
			while (resultSet.next()) {
				listUserHistory.add(parseUserHistory(resultSet));
			}
		} catch (SQLException e) {
			System.out.println("Error" + this.getClass().getName() + ":" + e.getMessage()); // e.printStackTrace();
		} finally {
			if (resultSet != null) {
				try {
					resultSet.close();
				} catch (SQLException throwables) {
					throwables.printStackTrace();
				}
			}
		}
		return listUserHistory;
	}

	@Override
	public Optional<UserHistory> findById(long recordId) {
		return Optional.ofNullable(findOne(recordId));
	}

	@Override
	public UserHistory findOne(Long itemId) {
		//search expression
		final String searchByIDQuery = "select * from m_user_history where id = ? order by id desc";
		//Result
		UserHistory returnUserHistory = null;
		//ResultSet
		ResultSet resultSet = null;
		try (
				Connection connection = dataSource.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(searchByIDQuery);
		) {
			preparedStatement.setLong(1, itemId);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				returnUserHistory = parseUserHistory(resultSet);
			} else {
				throw new ResourceNotFoundException("User history with id " + itemId + " not found");
			}
		} catch (SQLException e) {
			System.out.println("findByID Error " + e.getMessage());
		} finally {
			//Заккрываем resultSet для избежания утечек памяти
			try {
				if (resultSet != null) resultSet.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return returnUserHistory;
	}


	@Override
	public UserHistory save(UserHistory userHistory) {
		final String saveQuery = "insert into m_user_history ( user_id, date, weight, height) values( ?, ?, ?, ?)";
		final String getLastInsertId = "select currval('m_user_history_id_seq') as last_insert_id";
		ResultSet resultSet = null;
		try (Connection connection = dataSource.getConnection();
		     PreparedStatement insertStatement = connection.prepareStatement(saveQuery);
		     PreparedStatement getLastIdStatement = connection.prepareStatement(getLastInsertId);
		) {
			//Добавляем
			insertStatement.setLong(1, userHistory.getUserId());
			insertStatement.setTimestamp(2, userHistory.getDate());
			insertStatement.setInt(3, userHistory.getWeight());
			insertStatement.setInt(4, userHistory.getHeight());
			insertStatement.executeUpdate();
			//Получаем последний сохранённый ID
			resultSet = getLastIdStatement.executeQuery();
			resultSet.next();
			long insertedUserId = resultSet.getLong("last_insert_id");
			return findOne(insertedUserId);
		} catch (SQLException e) {
			System.out.println("save Error " + e.getMessage());
		} finally {
			try {
				if (resultSet != null) resultSet.close();
			} catch (SQLException throwables) {
				throwables.printStackTrace();
			}
		}
		return null;
	}

	@Override
	public UserHistory update(UserHistory item) {
		final String updateQuery = "update m_user_history set user_id = ?, date = ?, weight = ?, height = ? where id = ?";
		try (Connection connection = dataSource.getConnection();
		     PreparedStatement preparedStatement = connection.prepareStatement(updateQuery);
		) {
			preparedStatement.setLong(1, item.getUserId());
			preparedStatement.setTimestamp(2, item.getDate());
			preparedStatement.setInt(3, item.getWeight());
			preparedStatement.setInt(4, item.getHeight());
			preparedStatement.setLong(5, item.getId());
			preparedStatement.executeUpdate();
			return findOne(item.getId());
		} catch (SQLException e) {
			throw new RuntimeException("user update failed");
		}
	}

	@Override
	public int delete(UserHistory item) {
		final String deleteQuery = "delete from m_user_history where id=?";
		try (Connection connection = dataSource.getConnection();
		     PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery);
		) {
			preparedStatement.setLong(1, item.getId());
			preparedStatement.executeUpdate();
			return 1;
		} catch (SQLException e) {
			throw new RuntimeException("user delete failed");
		}
	}

	/**
	 * batch insert
	 * @param userHistories list of users needed to insert into table
	 * @return number or users inserted.
	 * if an error occurs, no users will be added
	 * */
//	@Override
//	public int insertBatch(List<UserHistory> userHistories) {
//		final int batchSize = 20;
//		int counter = 0;
//		boolean autoCommit;
//
//		String saveQuery = "insert into m_user_history ( user_id, date, weight, height) values( ?, ?, ?, ?)";
//		try (Connection connection = dataSource.getConnection();
//		     PreparedStatement insertStatement = connection.prepareStatement(saveQuery);
//		) {
//			//Запрещаем авто-комммит
//			autoCommit = connection.getAutoCommit();
//			connection.setAutoCommit(false);
//
//			for (UserHistory userHistory : userHistories) {
//				insertStatement.setLong(1, userHistory.getUserId());
//				insertStatement.setTimestamp(2, userHistory.getDate());
//				insertStatement.setInt(3, userHistory.getWeight());
//				insertStatement.setInt(4, userHistory.getHeight());
//
//				insertStatement.addBatch();
//				//исполнять будем каждые batchSize записей
//				if (++counter % batchSize == 0) {
//					insertStatement.executeBatch();
//				}
//			}
//			insertStatement.executeBatch();
//			connection.commit();
//			connection.setAutoCommit(autoCommit);
//			return counter;
//		} catch (SQLException e) {
//			System.out.println("batch insert Error " + e.getMessage());
//		}
//		return 0;
//	}


	/**
	 * parse ResultSet to User
	 *
	 * @param resultSet
	 * @return User
	 */
	private UserHistory parseUserHistory(ResultSet resultSet) throws SQLException {
		UserHistory userHistory = new UserHistory();
		userHistory.setId (resultSet.getLong(USER_HIST_ID));
		userHistory.setUserId (resultSet.getLong(USER_HIST_USER_ID));
		userHistory.setDate (resultSet.getTimestamp(USER_HIST_DATE));
		userHistory.setHeight (resultSet.getInt(USER_HIST_HEIGHT));
		userHistory.setWeight (resultSet.getInt(USER_HIST_WEIGHT));
		return userHistory;
	}

}
