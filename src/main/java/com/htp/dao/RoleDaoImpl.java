package com.htp.dao;

import com.htp.domain.Role;
import com.htp.exceptions.ResourceNotFoundException;
import com.htp.util.DatabaseConfiguration;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.htp.util.DatabaseConfiguration.*;

//Аннотация указывает спрингу, что класс применяется для доступа к базе данных (DAO)
@Repository
public class RoleDaoImpl implements RoleDao {
	//Наименования колонок в таблице m_roles
	public static final String ROLE_ID = "id";
	public static final String ROLE_NAME = "role_name";
	public static DatabaseConfiguration databaseConfig = DatabaseConfiguration.getInstance();

	private DataSource dataSource;

	public RoleDaoImpl(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public List<Role> findAll() {
		final String findAllQuery = "select * from m_roles order by id desc";
		List<Role> returnList = new ArrayList<>();
		ResultSet resultSet = null;
		try (
				Connection connection = dataSource.getConnection();
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
		//search expression
		final String searchQuery = "select * from m_roles where id > ? order by id desc";
		//лист для хранения результата поиска
		List<Role> resultList = new ArrayList<>();
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
		//search expression
		final String searchByIDQuery = "select * from m_roles where id = ? order by id desc";
		//Result
		Role returnRole = null;
		//ResultSet
		ResultSet resultSet = null;
		try (
				Connection connection = dataSource.getConnection();
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

		ResultSet resultSet = null;
		try (Connection connection = dataSource.getConnection();
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

		try (Connection connection = dataSource.getConnection();
		     PreparedStatement preparedStatement = connection.prepareStatement(updateQuery);
		) {
			preparedStatement.setString(1, role.getRoleName());
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
