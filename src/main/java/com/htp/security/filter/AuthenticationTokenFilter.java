//security
package com.htp.security.filter;

import com.htp.security.ApplicationHeaders;
import com.htp.security.util.TokenUtils;
import io.micrometer.core.instrument.util.StringUtils;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/*Этот фильтр будет принимать ве запросы пользователя и искать в заголовке токен*/

//Для использования нашего, кастомного фильтра наследуемся от UsernamePasswordAuthenticationFilter
//и переопределяем метод doFilter

//@Component
public class AuthenticationTokenFilter extends UsernamePasswordAuthenticationFilter {
	private final TokenUtils tokenUtils;
	private final UserDetailsService userDetailsService;

	public AuthenticationTokenFilter(TokenUtils tokenUtils, @Qualifier("userDetailServiceImpl") UserDetailsService userDetailsService) {
		this.tokenUtils = tokenUtils;
		this.userDetailsService = userDetailsService;
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {

		final HttpServletRequest httpReq = (HttpServletRequest) req;
		final String authToken = httpReq.getHeader(ApplicationHeaders.AUTH_TOKEN);

		//Всё проверки идут если в принципе есть токен. А то даже до сваггера добраться нельзя
		if (StringUtils.isNotBlank(authToken)) {
			final String userNameFromToken = tokenUtils.getUserNameFromToken(authToken);

			//Проверяем, есть ли у нас токен и не повреждён ли он, и проверем, что в контексте его нет
			//если токен валидный, в контекст отправляем
			//Почти такое же действие с контекстом производится в AuthController
			if (StringUtils.isNotBlank(userNameFromToken) &&
					SecurityContextHolder.getContext().getAuthentication() == null) {
				final UserDetails userDetails = userDetailsService.loadUserByUsername(userNameFromToken);
				if (tokenUtils.validateToken(authToken, userDetails)) {
					final UsernamePasswordAuthenticationToken usernamePasswordAuthentication = new UsernamePasswordAuthenticationToken(
							userDetails,
							null,
							userDetails.getAuthorities()
					);
					SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthentication);
				}
			}
		}

		chain.doFilter(req, res);
	}
}

