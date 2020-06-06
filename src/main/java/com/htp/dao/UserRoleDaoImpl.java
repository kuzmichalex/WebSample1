package com.htp.dao;

import com.htp.domain.UserRole;
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

/**
 * class for working with l_userRoles_roles table entries
 */

//Аннотация указывает спрингу, что класс применяется для доступа к базе данных (DAO)
@Repository
public class UserRoleDaoImpl implements UserRoleDao {
	//Наименования колонок в таблице m_userRoles
	public static final String USER_ROLE_ID = "id";
	public static final String USER_ID = "user_id";
	public static final String ROLE_ID = "role_id";

	private DataSource dataSource;

	public UserRoleDaoImpl(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public List<UserRole> findAll() {
		final String findAllQuery = "select * from l_user_roles";
		List<UserRole> listUserRoles = new ArrayList<>();
		ResultSet resultSet = null;

		try (Connection connection = dataSource.getConnection();
		     //3. Готовим выражение
		     PreparedStatement preparedStatement = connection.prepareStatement(findAllQuery)
		) {
			//4. Выполняем выражение
			resultSet = preparedStatement.executeQuery();
			//5. Вычитываем результат
			while (resultSet.next()) {
				listUserRoles.add(parseUserRoles(resultSet));
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
		return listUserRoles;
	}

	@Override
	public List<UserRole> search(String paramSearch) {
		final String searchQuery = "select * from l_user_roles where id > ? order by id desc";
		//лист для хранения результата поиска
		List<UserRole> resultList = new ArrayList<>();
		//сет для получения результата выборки
		ResultSet resultSet = null;

		try (   //Устанавливаем соединение
		        Connection connection = dataSource.getConnection();
		        PreparedStatement preparedStatement = connection.prepareStatement(searchQuery);
		) {
			//Готовим выражение
			preparedStatement.setLong(1, Long.parseLong(paramSearch));
			//Выполняем выражение
			resultSet = preparedStatement.executeQuery();
			//получаем из resultSet список пользователей
			while (resultSet.next()) {
				resultList.add(parseUserRoles(resultSet));
			}
		} catch (SQLException e) {
			System.out.println("Error" + this.getClass().getName() + ":" + e.getMessage()); // e.printStackTrace();
		} finally {
			try {
				if (resultSet != null) resultSet.close();
			} catch (SQLException throwables) {
				throwables.printStackTrace();
			}
		}
		return resultList;
	}

	@Override
	public Optional<UserRole> findById(long userRolesID) {
		return Optional.ofNullable(findOne(userRolesID));
	}

	@Override
	public UserRole findOne(long userRolesID) {
		//search expression
		final String searchByIDQuery = "select * from l_user_roles where id = ? order by id desc";
		//Result
		UserRole returnUserRoles = null;
		//ResultSet
		ResultSet resultSet = null;
		try (
				Connection connection = dataSource.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(searchByIDQuery);
		) {
			preparedStatement.setLong(1, userRolesID);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				returnUserRoles = parseUserRoles(resultSet);
			} else {
				throw new ResourceNotFoundException("UserRoles with id " + userRolesID + " not found");
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
		return returnUserRoles;
	}

	@Override
	public UserRole save(UserRole userRoles) {
		final String saveQuery = "insert into l_user_roles ( user_id, role_id) values( ?, ? )";
		final String getLastInsertId = "select currval('l_user_roles_id_seq') as last_insert_id";
		ResultSet resultSet = null;
		try (Connection connection = dataSource.getConnection();
		     PreparedStatement insertStatement = connection.prepareStatement(saveQuery);
		     PreparedStatement getLastIdStatement = connection.prepareStatement(getLastInsertId);
		) {
			//Добавляем
			insertStatement.setLong(1, userRoles.getUserId());
			insertStatement.setLong(2, userRoles.getRoleId());
			insertStatement.executeUpdate();
			//Получаем последний сохранённый ID
			resultSet = getLastIdStatement.executeQuery();
			resultSet.next();
			long insertedUserRolesId = resultSet.getLong("last_insert_id");
			return findOne(insertedUserRolesId);
		} catch (SQLException e) {
			System.out.println("save UserRole Error " + e.getMessage());
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
	public UserRole update(UserRole userRoles) {
		final String updateQuery = "update l_user_roles set user_id = ?, role_id = ? where id = ?";
		try (Connection connection = dataSource.getConnection();
		     PreparedStatement preparedStatement = connection.prepareStatement(updateQuery);
		) {
			preparedStatement.setLong(1, userRoles.getRoleId());
			preparedStatement.setLong(2, userRoles.getUserId());
			preparedStatement.setLong(3, userRoles.getId());
			preparedStatement.executeUpdate();
			return findOne(userRoles.getId());
		} catch (SQLException e) {
			throw new RuntimeException("userRoles update failed");
		}
	}

	@Override
	public int Delete(UserRole userRoles) {
		final String deleteQuery = "delete from l_user_roles where id=?";
		try (Connection connection = dataSource.getConnection();
		     PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery);
		) {
			preparedStatement.setLong(1, userRoles.getId());
			preparedStatement.executeUpdate();
			return 1;
		} catch (SQLException e) {
			throw new RuntimeException("userRoles delete failed");
		}
	}


	/**
	 * batch insert
	 *
	 *@param userRoles list of userRoless needed to insert into table
	 * @return number or userRoless inserted.
	 * if an error occurs, no userRoless will be added
	 */
	@Override
	public int insertBatch(List<UserRole> userRoles) {
		final int batchSize = 20;
		int counter = 0;
		boolean autoCommit;

		String saveQuery = "insert into l_user_roles ( user_id, role_id) values( ?, ?)";
		try (Connection connection = dataSource.getConnection();
		     PreparedStatement insertStatement = connection.prepareStatement(saveQuery);
		) {
			//Запрещаем авто-комммит
			autoCommit = connection.getAutoCommit();
			connection.setAutoCommit(false);

			for (UserRole userRoleRec : userRoles) {
				insertStatement.setString(1, Long.toString(userRoleRec.getUserId()));
				insertStatement.setString(2, Long.toString(userRoleRec.getRoleId()));

				insertStatement.addBatch();
				//исполнять будем каждые batchSize записей
				if (++counter % batchSize == 0) {
					insertStatement.executeBatch();
				}
			}
			insertStatement.executeBatch();
			connection.commit();
			connection.setAutoCommit(autoCommit);
			return counter;
		} catch (SQLException e) {
			System.out.println("batch insert Error " + e.getMessage());
		}
		return 0;
	}

	/**
	 * parse ResultSet to UserRoles
	 *
	 * @param resultSet
	 * @return UserRoles
	 */
	private UserRole parseUserRoles(ResultSet resultSet) throws SQLException {
		UserRole userRoles = new UserRole();
		userRoles.setId(resultSet.getLong(USER_ROLE_ID));
		userRoles.setUserId(Long.parseLong(resultSet.getString(USER_ID)));
		userRoles.setRoleId(Long.parseLong(resultSet.getString(ROLE_ID)));
		return userRoles;
	}

}