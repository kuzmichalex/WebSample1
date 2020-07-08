//JD18 Hibernate создаём 2 бина: SessionFactory и EntityManagerFactory

package com.htp.util;

import com.htp.configuration.ApplicationMainConfig;
import com.htp.configuration.SwaggerConfig;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Primary;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.sql.DataSource;


//@SpringBootApplication определяет автоматическое сканирование пакета, где находится класс
//  Чтобы сканировались другие пакеты, указываем scanBasePackages
//@EnableSwagger2 - разрешаем /swagger-ui.html
//@EnableTransactionManagement разрешаем использовать @Transactional ???
//@EnableAspectJAutoProxy ??? И без него работает

@EnableSwagger2
@EnableAspectJAutoProxy(proxyTargetClass = true)
@EnableTransactionManagement(proxyTargetClass = true)
@SpringBootApplication(scanBasePackages = {"com.htp"},
		exclude = {
				//JacksonAutoConfiguration.class,
				HibernateJpaAutoConfiguration.class
		})
@Import({
		ApplicationMainConfig.class,
		//DatasourceConfiguration.class,
		SwaggerConfig.class
})
public class SpringBootStarterApplication {
	public static void main(String[] args) {
		SpringApplication.run(SpringBootStarterApplication.class, args);
	}

	@Bean(name = "sessionFactory")
	public SessionFactory getSessionFactory(DataSource dataSource) throws Exception {
		// Fix Postgres JPA Error:
		// Method org.postgresql.jdbc.PgConnection.createClob() is not yet implemented.
		// properties.put("hibernate.temp.use_jdbc_metadata_defaults",false);

		LocalSessionFactoryBean factoryBean = new LocalSessionFactoryBean();

		// Package contain entity classes
		factoryBean.setPackagesToScan("com.htp");
		factoryBean.setDataSource(dataSource);
		factoryBean.setAnnotatedPackages("com.htp");
		factoryBean.afterPropertiesSet();
		//
		SessionFactory sf = factoryBean.getObject();
		System.out.println("## getSessionFactory: " + sf);
		return sf;
	}

	//Entity Manager

	@Autowired
	@Primary
	@Bean(name = "entityManagerFactory")
	public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource) {
		LocalContainerEntityManagerFactoryBean em
				= new LocalContainerEntityManagerFactoryBean();
		em.setDataSource(dataSource);
		em.setPackagesToScan("com.htp");

		JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		em.setJpaVendorAdapter(vendorAdapter);

		return em;
	}
}