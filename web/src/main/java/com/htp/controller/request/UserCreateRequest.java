package com.htp.controller.request;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.sql.Date;

//@Data - генерит equals, hashCode, toString, getter + setter и конструктор с аргументами
// при этом обязательными полями будут поля final или @NotNull
//@NoArgsConstructor - конструктр бе заргументов
//@Builder - генерируется билдер

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ApiModel(description = "User creation model")
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class UserCreateRequest {

	@NotNull
	@NotEmpty
	@Size(min = 1, max = 50)
	@ApiModelProperty(required = true, dataType = "string", notes = "user login")
	private String login;

	@NotNull
	@NotEmpty
	@Size(min = 1, max = 100)
	@ApiModelProperty(required = true, dataType = "string", notes = "user name")
	private String name;

	@NotNull
	//@NotEmpty --Ругается, ячо для типа Date NotEmpty валидатора нет.
	@ApiModelProperty(required = true, dataType = "Date", notes = "user birth date")
	private Date birthDate;

	@NotNull
	@NotEmpty
	@Size(min = 1, max = 100)
	@ApiModelProperty(required = true, dataType = "string", notes = "user name")
	private String password;

}
