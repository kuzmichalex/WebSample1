//JD18 Hibernate создаём 2 бина: SessionFactory и EntityManagerFactory

package com.htp.util;

import com.github.benmanes.caffeine.cache.Caffeine;
import com.htp.configuration.ApplicationMainConfig;
import com.htp.configuration.SwaggerConfig;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.concurrent.TimeUnit;


//@SpringBootApplication определяет автоматическое сканирование пакета, где находится класс
//  Чтобы сканировались другие пакеты, указываем scanBasePackages
//@EnableSwagger2 - разрешаем /swagger-ui.html
//@EnableTransactionManagement разрешаем использовать @Transactional Это включит проксирование, которое нужно для Transactional
//@EnableAspectJAutoProxy ??? И без него работает

@EnableCaching
@EnableSwagger2
@EnableAspectJAutoProxy(proxyTargetClass = true)
@EnableTransactionManagement(proxyTargetClass = true)
@SpringBootApplication(scanBasePackages = {"com.htp"})
@EnableJpaRepositories("com.htp.dao")
@Import({
		ApplicationMainConfig.class,
		//DatasourceConfiguration.class,
		SwaggerConfig.class
})
public class SpringBootStarterApplication {
	private static final String SCAN_PACHAGES = "com.htp";

	public static void main(String[] args) {
		SpringApplication.run(SpringBootStarterApplication.class, args);
	}

	@Bean(name = "sessionFactory")
	public SessionFactory getSessionFactory(DataSource dataSource) throws IOException {
	/*	 Fix Postgres JPA Error:
		 Method org.postgresql.jdbc.PgConnection.createClob() is not yet implemented.
		 "properties.put("hibernate.temp.use_jdbc_metadata_defaults",false);" */

		LocalSessionFactoryBean factoryBean = new LocalSessionFactoryBean();

		// Package contain entity classes
		factoryBean.setPackagesToScan(SCAN_PACHAGES);
		factoryBean.setDataSource(dataSource);
		factoryBean.setAnnotatedPackages(SCAN_PACHAGES);
		factoryBean.afterPropertiesSet();
		return factoryBean.getObject();
	}

	//Entity Manager
	@Autowired
	@Primary
	@Bean(name = "entityManagerFactory")
	public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource) {
		LocalContainerEntityManagerFactoryBean em
				= new LocalContainerEntityManagerFactoryBean();
		em.setDataSource(dataSource);
		em.setPackagesToScan(SCAN_PACHAGES);

		JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		em.setJpaVendorAdapter(vendorAdapter);

		return em;
	}


	@Bean
	public CacheManager cacheManager() {
		CaffeineCacheManager usersAdmins = new CaffeineCacheManager("usersAdmins");
		usersAdmins.setCaffeine(cacheProperties());
		return usersAdmins;
	}

	public Caffeine<Object, Object> cacheProperties() {
		return Caffeine.newBuilder()
				.initialCapacity(10)
				.maximumSize(50)
				.expireAfterAccess(10, TimeUnit.MINUTES)
				.weakKeys()
				.recordStats();
	}


}