//security
package com.htp.security.util;

import com.htp.configuration.JwtTokenConfiguration;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

/*
 * класс для работы с jwt-токеном. Нужен для 1. генерацуии токена на основе пользовательских данных
 * и 2. для извлечения из токена всяких полезных данных
 */

@Component
@RequiredArgsConstructor
public class TokenUtils {

	//строка payload токена, содерщащая дату создания токена
	public static final String CREATE_VALUE = "created";
	//строка payload токена, содержащая список ролей ползователя
	public static final String ROLES = "roles";
	//строка payload, содержащая имя залогиненного пользователя
	public static final String SUBJECT = "sub";

	private final JwtTokenConfiguration jwtTokenConfig;

	public String getUserNameFromToken(String token) {
		final Claims claimsFromToken = getClaimsFromToken(token);
		return claimsFromToken.getSubject();
	}

	public Date getCreateDateFromToken(String token) {
		return (Date) getClaimsFromToken(token).get(CREATE_VALUE);
	}

	public Date getExpirationDateFromToken(String token) {
		return getClaimsFromToken(token).getExpiration();
	}

	/*разбор токен составные части; в мапку*/
	private Claims getClaimsFromToken(String token) {
		return Jwts.
				parser()
				.setSigningKey(jwtTokenConfig.getSecret())
				.parseClaimsJws(token)
				.getBody();

	}

	private Date generateCurrentDate() {
		return new Date();
	}

	/*Получение даты прекращения действия токена: текущая дата + время жизни токена из application.yml*/
	private Date generateExpirationDate() {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MILLISECOND, jwtTokenConfig.getExpire());
		return calendar.getTime();
	}

	private boolean isTokenExpired(String token) {
		final Date expire = getExpirationDateFromToken(token);
		return expire.before(this.generateCurrentDate());
	}

	/*Генерация токена со значениями из мапки и secret из application.yml*/
	private String generateToken(Map<String, Object> claims) {
		return Jwts
				.builder()
				.setClaims(claims)
				.setExpiration(generateExpirationDate())
				.signWith(SignatureAlgorithm.HS512, jwtTokenConfig.getSecret())
				.compact();
	}

	/*Подготовка мапки со значениями из UserDetails и генерация токена */
	public String generateToken(UserDetails userDetails) {
		Map<String, Object> claims = new HashMap<>();
		claims.put(SUBJECT, userDetails.getUsername());
		claims.put(ROLES, getUserRoles(userDetails));
		claims.put(CREATE_VALUE, generateCurrentDate());
		return generateToken(claims);
	}

	/*Получение списка ролей пользователя из его userDetails
		Хммм... изначально метод назывался getEncryptedRoles.
	    Сдаётся мне, что в стриме чего-то не хватает.
	    Чего-то было удалено такое, что дополнительно прятало спис ок ролей?
		Интересно, почему.
		Может, стоит сделать так, чтобы и длина пейлоада была у всех одинаковой, чтобы
		по его длине нельзя было опознать пользователя с крутыми правами?
	 */
	private List<String> getUserRoles(UserDetails userDetails) {
		final Collection<? extends GrantedAuthority> authorities = userDetails.getAuthorities();
		return authorities.stream()
				.map(GrantedAuthority::getAuthority)
				.map(a -> a.replace("ROLE_", ""))
				.map(String::toUpperCase)
				.collect(Collectors.toList());
	}

	public Boolean validateToken(String token, UserDetails userDetails) {
		final String userName = getUserNameFromToken(token);
		return userName.equals(userDetails.getUsername());
	}
}
