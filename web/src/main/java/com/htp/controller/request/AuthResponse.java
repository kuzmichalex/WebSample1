//security
package com.htp.controller.request;


/*Класс нужен для ответа по аутентификация
*
* */

import io.swagger.annotations.ApiModel;
import lombok.Builder;
import lombok.Data;

@Data
@ApiModel(description = "Authentication response witch user ID and JWTtoken")
@Builder
public class AuthResponse {

	private String login;
	private String jwtToken;

}
