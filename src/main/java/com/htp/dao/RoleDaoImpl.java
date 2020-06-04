package com.htp.dao;

import com.htp.domain.Role;
import com.htp.domain.User;
import com.htp.exceptions.ResourceNotFoundException;
import com.htp.util.DatabaseConfiguration;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.htp.util.DatabaseConfiguration.*;

public class RoleDaoImpl implements RoleDao {
	//Наименования колонок в таблице m_roles
	public static final String ROLE_ID = "id";
	public static final String ROLE_NAME = "role_name";
	public static DatabaseConfiguration databaseConfig = DatabaseConfiguration.getInstance();

	@Override
	public List<Role> findAll() {
		String driverName = databaseConfig.getProperty(DATABASE_DRIVER_NAME);
		String url = databaseConfig.getProperty(DATABASE_URL);
		String user = databaseConfig.getProperty(DATABASE_LOGIN);
		String password = databaseConfig.getProperty(DATABASE_PASSWORD);

		final String findAllQuery = "select * from m_roles order by id desc";
		List<Role> returnList = new ArrayList<>();
		ResultSet resultSet = null;

		try {
			Class.forName(driverName);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		try (
				Connection connection = DriverManager.getConnection(url, user, password);
				PreparedStatement preparedStatement = connection.prepareStatement(findAllQuery)
		) {
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				returnList.add(parseRole(resultSet));
			}

		} catch (SQLException throwables) {
			throwables.printStackTrace();
		} finally {
			try {
				if (resultSet != null) resultSet.close();
			} catch (SQLException throwables) {
				throwables.printStackTrace();
			}
		}
		return returnList;
	}

	private Role parseRole(ResultSet resultSet) throws SQLException {
		Role role = new Role();
		role.setId(resultSet.getLong(ROLE_ID));
		role.setRoleName(resultSet.getString(ROLE_NAME));
		return role;
	}

	@Override
	public List<Role> search(String paramSearch) {
		//database configuration
		String driverName = databaseConfig.getProperty(DATABASE_DRIVER_NAME);
		String url = databaseConfig.getProperty(DATABASE_URL);
		String login = databaseConfig.getProperty(DATABASE_LOGIN);
		String password = databaseConfig.getProperty(DATABASE_PASSWORD);
		//search expression
		final String searchQuery = "select * from m_roles where id > ? order by id desc";
		//лист для хранения результата поиска
		List<Role> resultList = new ArrayList<>();
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
				resultList.add(parseRole(resultSet));
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
	public Optional<Role> findById(long roleID) {
		return Optional.ofNullable(findOne(roleID));
	}

	@Override
	public Role findOne(long roleID) {
		//database configuration
		String driverName = databaseConfig.getProperty(DATABASE_DRIVER_NAME);
		String url = databaseConfig.getProperty(DATABASE_URL);
		String login = databaseConfig.getProperty(DATABASE_LOGIN);
		String password = databaseConfig.getProperty(DATABASE_PASSWORD);
		//search expression
		final String searchByIDQuery = "select * from m_roles where id = ? order by id desc";
		//Result
		Role returnRole = null;
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
			preparedStatement.setLong(1, roleID);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				returnRole = parseRole(resultSet);
			} else {
				throw new ResourceNotFoundException("User with id " + roleID + " not found");
			}
		} catch (SQLException e) {
			System.out.println("Role findByID Error " + e.getMessage());
		} finally {
			//Заккрываем resultSet для избежания утечек памяти
			try {
				if (resultSet != null) resultSet.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return returnRole;
	}

	@Override
	public Role save(Role role) {
		final String saveQuery = "insert into m_roles ( role_name ) values ?";
		final String getLastInsertId = "select currval('m_roles_id_seq')";

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
			insertStatement.setString(1, role.getRoleName());
			insertStatement.executeUpdate();
			//Получаем последний сохранённый ID
			resultSet = getLastIdStatement.executeQuery();
			resultSet.next();
			long insertedRoleId = resultSet.getLong("last_insert_id");
			return findOne(insertedRoleId);
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
	public Role update(Role role) {
		final String updateQuery = "update m_roles set role_name = ?";

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
			preparedStatement.setString(1,role.getRoleName());
			preparedStatement.executeUpdate();
			return findOne(role.getId());
		} catch (SQLException e) {
			throw new RuntimeException("role update failed");
		}
	}

	@Override
	public int Delete(Role role) {
		return 0;
	}
}
