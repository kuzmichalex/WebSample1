//security
package com.htp.security.util;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.security.Principal;
import java.util.Collection;

/* Послу успошного лоигна в спринг секьюрити констексте будет находиться информация об этом логине
 *  интерфейсная ссылка  principal позволяет не трогая токен обратиться к контексту и повытаскивать
 * информацию о логине, пароле и аусоритес
 * Класс нужен для более удобного доступа к этим данным. То есть, например, в контроллере
 * мы можем прописать параметр Principal principal, и можем пользоваться. Удобно! */

public class PrincipalUtil {
	public static String getUserLogin(Principal principal) {
		Object castedPrincipal = ((UsernamePasswordAuthenticationToken) principal).getPrincipal();
		return ((User) castedPrincipal).getUsername();
	}

	public static String getPassword(Principal principal) {
		Object castedPrincipal = ((UsernamePasswordAuthenticationToken) principal).getPrincipal();
		return ((User) castedPrincipal).getPassword();
	}

	public static Collection<GrantedAuthority> getAutorities(Principal principal) {
		Object castedPrincipal = ((UsernamePasswordAuthenticationToken) principal).getPrincipal();
		return ((User) castedPrincipal).getAuthorities();
	}

}

