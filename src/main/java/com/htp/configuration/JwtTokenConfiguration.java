//security

package com.htp.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;


/* Вычитываем из application.yml информацию для генерации jwt-токена
*  Это будут т.н. secret и expire
* */
@Data
@Configuration
@ConfigurationProperties("jwttoken")
public class JwtTokenConfiguration {
	private String secret;
	private int expire;

}
