package com.htp.controller.request;
//@Data - генерит equals, hashCode, toString, getter + setter и конструктор с аргументами
// при этом обязательными полями будут поля final или @NotNull
//@NoArgsConstructor - конструктр бе заргументов
//@Builder - генерируется билдер
//@ApiModel - описываем модель данных
//@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY) - устанавливаем видимость всех полей.


import com.fasterxml.jackson.annotation.JsonAutoDetect;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ApiModel(description = "User creation model")
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class RoleCreateRequest {
	private String roleName;
}
