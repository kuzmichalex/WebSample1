package com.htp.configuration;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.sql.DataSource;

/**
 * конфигурация источника данных
 */

/*@Configuration указывает спрингу, что класс хранит конфигурации
	@PropertySource указывает спрингу на файл конфигурации
	@Value отмеченные этой аннотацией переменные будут
	проиницализированы спрингом значениями из PropertySource*/


@Configuration
@PropertySource("classpath:database.properties")
public class DatasourceConfiguration {

	@Value("${driverName}")
	private String driverName;

	@Value("${url}")
	private String url;

	@Value("${login}")
	private String login;

	@Value("${password}")
	private String password;

	@Value("${initialSize}")
	private Integer initialSize;

	/**
	 * Метод, с помощью которого получим объект DataSource
	 * Для получения нам нужен конфиг, получим и проинициализруем его
	 * Аннотация Bean указывает спригну, что этот метод создаёт и настраивает новый объект
	 * Методы @Bean в классах @Configuration обрабаываются спрингом в полном режиме
	 */
	@Bean
	public DataSource generateDatasource() {
		//Создаём и настраиваем конфигурацию для пула коннекшонов
		HikariConfig hikariConfig = new HikariConfig();
		hikariConfig.setDriverClassName(driverName);
		hikariConfig.setJdbcUrl(url);
		hikariConfig.setUsername(login);
		hikariConfig.setPassword(password);
		hikariConfig.setMaximumPoolSize(initialSize);
		//Создаём пул коннекшонов
		return new HikariDataSource(hikariConfig);
	}

	/***
	 * Методы, с помощью которых будем инициализоровать
	 *
	 * */
	@Bean
	public JdbcTemplate jdbcTemplate(DataSource dataSource) {
		return new JdbcTemplate(dataSource);
	}

	@Bean
	public NamedParameterJdbcTemplate namedParameterJdbcTemplate(DataSource dataSource) {
		return new NamedParameterJdbcTemplate(dataSource);
	}
}
