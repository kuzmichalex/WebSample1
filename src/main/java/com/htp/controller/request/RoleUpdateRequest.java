package com.htp.controller.request;
//@Data - генерит equals, hashCode, toString, getter + setter и конструктор с аргументами
// при этом обязательными полями будут поля final или @NotNull
//@NoArgsConstructor - конструктр бе заргументов
//@Builder - генерируется билдер
//@ApiModel - описываем модель данных
//@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY) - устанавливаем видимость всех полей.


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

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ApiModel(description = "User creation model")
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class RoleUpdateRequest {

	@NotNull
	@ApiModelProperty(required = true, dataType = "long", notes = "Role ID")
	private long id;

	@NotNull
	@NotEmpty
	@Size(min = 1, max = 50)
	@ApiModelProperty(required = true, dataType = "string", notes = "Role Name")
	private String roleName;
}
