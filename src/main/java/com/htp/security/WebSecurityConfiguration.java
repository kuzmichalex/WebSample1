//security

package com.htp.security;

import com.htp.security.filter.AuthenticationTokenFilter;
import com.htp.security.util.TokenUtils;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/*
 * Аннтотация EnableWebSecurity указывает, что этот класс будет использоваться для построения фильтров
 * Тут описывается доступность URL для указанных ролей
 * Информация отсюда используется в Authentication manager
 *
 * наследование от WebSecurityConfigurerAdapter мы можем в общей web security configuration указать
 * места, работы с колторыми возможна только для... кого надо, того и укажем
 * */

@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {
	private UserDetailsService userDetailsService;
	private TokenUtils tokenUtils;

	public WebSecurityConfiguration(@Qualifier("userDetailServiceImpl") UserDetailsService userDetailsService,
	                                TokenUtils tokenUtils) {
		this.userDetailsService = userDetailsService;
		this.tokenUtils = tokenUtils;
	}

	/* В контроллере аутентификации нам нужен AuthenticationManager. Получим его тут
	* В контроллере нам нужно вызвать метод Authenticate, а сам AuthenticationManager скрыт.
	* Этим бином мы перекладываем в спринг контекст - чтобы была возможность достучатсья.
	* То есть сами бин AuthenticationManager - создан, а при помощи этого бина добываем доступ к нему.
	* */
	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}


	/* Этот бин нужен потому, что напрямую в фильтр заавтовайрить нельзя
	* Поэтому здесь мы создаём AuthenticationTokenFilter */
	@Bean
	public AuthenticationTokenFilter authenticationTokenFilterBean(AuthenticationManager authenticationManager){
		final AuthenticationTokenFilter authenticationTokenFilter = new AuthenticationTokenFilter(tokenUtils, userDetailsService);
		authenticationTokenFilter.setAuthenticationManager(authenticationManager);
		return authenticationTokenFilter;
	}


	/* здесь мы указываем использвать для аутентификации наш сервис, который будет вытягивать информацию по пользователю из БД*/
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(NoOpPasswordEncoder.getInstance());
	}

	/* здесь настраиваем правила для Access authorisation filter*/
	/* Также есть возможность засекьюрить не просто урл, а контроллер или метод в контроллере
	  при помощи аннотациии вроде @Secured("ADMIN") */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.
				csrf().disable().               //Выключаем проверку при кроссдоменном запросе
				exceptionHandling().            //Включаем наш exception handler
				and().
				//sessionManagement() - информация о сессии. На не надо, тк информация о залогинивании мы храним в токене
				authorizeRequests().            //Указываем необходимость авторизировать все запросы
				antMatchers("/admin/**").hasAnyRole("ROLE_ADMIN").
				antMatchers("/actuator/**").permitAll().
				antMatchers("/auth/**").permitAll().
				antMatchers("/registration/**").permitAll().
				antMatchers("/logout/**").permitAll().
				antMatchers("/csrf/**").permitAll().
				antMatchers("swagger-ui.html#").permitAll().
				antMatchers("/v2/api-docs", "configuration/ui", "swagger-resources/", "configuration/security/", "/webjars/").permitAll().
				antMatchers("/users/**").permitAll().
				antMatchers(HttpMethod.OPTIONS, "/**").permitAll().      //разрешаем доступ ко всем options-запросам
				anyRequest().authenticated();

		//Добавляем бин в проверку перед всем
		http.addFilterBefore(authenticationTokenFilterBean(authenticationManagerBean()), UsernamePasswordAuthenticationFilter.class);
	}

	/* здесь мы указываем, какие урл не проверять вовсе*/
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().
				antMatchers(
						"/v2/api-docs/**",
						"/configuration/ui/**",
						"/swagger-resources/**",
						"/configuration/security/**",
						"/swagger-ui.html",
						"/actuator",
						"/csrf/**",
						"/webjars/**"
				);
	}
}
