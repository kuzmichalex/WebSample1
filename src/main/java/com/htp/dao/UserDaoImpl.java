package com.htp.dao;

import com.htp.domain.User;
import com.htp.exceptions.ResourceNotFoundException;
import com.htp.util.DatabaseConfiguration;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.htp.util.DatabaseConfiguration.*;

public class UserDaoImpl implements UserDao {
	//Наименования колонок в таблице m_user
	public static final String USER_ID = "id";
	public static final String USER_LOGIN = "login";
	public static final String USER_NAME = "name";
	public static final String USER_BIRTHDATE = "birth_date";
	public static final String USER_PASSWORD = "password";

	public static DatabaseConfiguration databaseConfig = DatabaseConfiguration.getInstance();

	@Override
	public List<User> findAll() {
		String driverName = databaseConfig.getProperty(DATABASE_DRIVER_NAME);
		String url = databaseConfig.getProperty(DATABASE_URL);
		String user = databaseConfig.getProperty(DATABASE_LOGIN);
		String password = databaseConfig.getProperty(DATABASE_PASSWORD);

		final String findAllQuery = " select * from m_users order by id desc";
		List<User> listUsers = new ArrayList<>();
		ResultSet resultSet = null;

		//1. Загрузка драйвера
		try {
			Class.forName(driverName);
		} catch (ClassNotFoundException e) {
			System.out.println("Can't load database driver " + driverName);
		}
		//2. Устанавливаем соединение
		try (Connection connection = DriverManager.getConnection(url, user, password);
		     //3. Готовим выражение
		     PreparedStatement preparedStatement = connection.prepareStatement(findAllQuery)
		) {
			//4. Выполняем выражение
			resultSet = preparedStatement.executeQuery();
			//5. Вычитываем результат
			while (resultSet.next()) {
				listUsers.add(parseUser(resultSet));
			}
		} catch (SQLException e) {
			System.out.println("Error" + this.getClass().getName() + ":" + e.getMessage()); // e.printStackTrace();
		}finally {
			if(resultSet != null) {
				try {
					resultSet.close();
				} catch (SQLException throwables) {
					throwables.printStackTrace();
				}
			}
		}
		return listUsers;
	}

	@Override
	public List<User> search(String paramSearch) {
		//database configuration
		String driverName = databaseConfig.getProperty(DATABASE_DRIVER_NAME);
		String url = databaseConfig.getProperty(DATABASE_URL);
		String login = databaseConfig.getProperty(DATABASE_LOGIN);
		String password = databaseConfig.getProperty(DATABASE_PASSWORD);
		//search expression
		final String searchQuery = "select * from m_users where id > ? order by id desc";
		//лист для хранения результата поиска
		List<User> resultList = new ArrayList<>();
		//сет для получения результата выборки
		ResultSet resultSet = null;

		//Loading driver
		try {
			Class.forName(driverName);
		} catch (ClassNotFoundException e) {
			System.out.println("Can't load database driver " + driverName);
		}
		try (   //Устанавливаем соединение
		        Connection connection = DriverManager.getConnection(url, login, password);
		        PreparedStatement preparedStatement = connection.prepareStatement(searchQuery);
		) {
			//Готовим выражение
			preparedStatement.setLong(1, Long.parseLong(paramSearch));
			//Выполняем выражение
			resultSet = preparedStatement.executeQuery();
			//получаем из resultSet список пользователей
			while (resultSet.next()) {
				resultList.add(parseUser(resultSet));
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
	public Optional<User> findById(long userID) {
		return Optional.ofNullable(findOne(userID));
	}

	@Override
	public User findOne(long userID) {
		//database configuration
		String driverName = databaseConfig.getProperty(DATABASE_DRIVER_NAME);
		String url = databaseConfig.getProperty(DATABASE_URL);
		String login = databaseConfig.getProperty(DATABASE_LOGIN);
		String password = databaseConfig.getProperty(DATABASE_PASSWORD);
		//search expression
		final String searchByIDQuery = "select * from m_users where id = ? order by id desc";
		//Result
		User returnUser = null;
		//ResultSet
		ResultSet resultSet = null;
		try {
			Class.forName(driverName);
		} catch (ClassNotFoundException e) {
			System.out.println("Can't load database driver " + driverName);
		}
		try (
				Connection connection = DriverManager.getConnection(url, login, password);
				PreparedStatement preparedStatement = connection.prepareStatement(searchByIDQuery);
		) {
			preparedStatement.setLong(1, userID);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				returnUser = parseUser(resultSet);
			} else {
				throw new ResourceNotFoundException("User with id " + userID + " not found");
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
		return returnUser;
	}

	@Override
	public User save(User user) {
		final String saveQuery = "insert into m_users ( login, name, birth_date, password) values ?, ?, ?, ?, ?";
		final String getLastInsertId = "select currval('m_users_id_seq')";

		String driverName = databaseConfig.getProperty(DATABASE_DRIVER_NAME);
		String url = databaseConfig.getProperty(DATABASE_URL);
		String login = databaseConfig.getProperty(DATABASE_LOGIN);
		String password = databaseConfig.getProperty(DATABASE_PASSWORD);
		try {
			Class.forName(driverName);
		} catch (ClassNotFoundException e) {
			System.out.println("Can't load database driver " + driverName);
		}

		ResultSet resultSet = null;
		try (Connection connection = DriverManager.getConnection(url, login, password);
		     PreparedStatement insertStatement = connection.prepareStatement(saveQuery);
		     PreparedStatement getLastIdStatement = connection.prepareStatement(getLastInsertId);
		) {
			//Добавляем
			insertStatement.setString(1, user.getLogin());
			insertStatement.setString(2, user.getName());
			insertStatement.setDate(3, user.getBirthDate());
			insertStatement.setString(4, user.getPassword());
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
	public User update(User user) {
		final String updateQuery = "update m_users set login = ?, name = ?, birth_date = ?, password = ? where id = ?";

		String driverName = databaseConfig.getProperty(DATABASE_DRIVER_NAME);
		String url = databaseConfig.getProperty(DATABASE_URL);
		String login = databaseConfig.getProperty(DATABASE_LOGIN);
		String password = databaseConfig.getProperty(DATABASE_PASSWORD);
		try {
			Class.forName(driverName);
		} catch (ClassNotFoundException e) {
			System.out.println("Can't load database driver " + driverName);
		}
		try (Connection connection = DriverManager.getConnection(driverName, login, password);
		     PreparedStatement preparedStatement = connection.prepareStatement(updateQuery);
		) {
			preparedStatement.setString(1, user.getLogin());
			preparedStatement.setString(2, user.getName());
			preparedStatement.setDate(3, user.getBirthDate());
			preparedStatement.setString(4, user.getPassword());
			preparedStatement.setLong(5, user.getId());
			preparedStatement.executeUpdate();
			return findOne(user.getId());
		} catch (SQLException e) {
			throw new RuntimeException("user update failed");
		}
	}

	@Override
	public int Delete(User user) {
		return 0;
	}

	/**
	 * parse ResultSet to User
	 *
	 * @param resultSet
	 * @return User
	 */
	private User parseUser(ResultSet resultSet) throws SQLException {
		User user = new User();
		user.setId(resultSet.getLong(USER_ID));
		user.setLogin(resultSet.getString(USER_LOGIN));
		user.setName(resultSet.getString(USER_NAME));
		user.setBirthDate(resultSet.getDate(USER_BIRTHDATE));
		user.setPassword(resultSet.getString(USER_PASSWORD));
		return user;
	}

}
