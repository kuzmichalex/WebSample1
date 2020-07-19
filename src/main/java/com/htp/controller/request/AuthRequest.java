//security
package com.htp.controller.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

/* Параметры, которые бюудет передавать пользователь в body для авторизации
* имплементация Serializable нужня длоя того, чтобы это всё преобразовывалось в json объект
* */

@Data
@ApiModel(description = "Authentication witch login and password")
public class AuthRequest implements Serializable {
	@NotEmpty
	@ApiModelProperty(required = true, allowableValues = "Admin", dataType = "string")
	private String userName;

	@NotEmpty
	@ApiModelProperty(required = true, allowableValues = "password", dataType = "string")
	private String password;

}
