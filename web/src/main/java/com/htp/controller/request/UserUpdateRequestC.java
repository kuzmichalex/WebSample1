package com.htp.controller.request;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import io.swagger.annotations.ApiModel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@ApiModel(description = "User update model")
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class UserUpdateRequestC extends UserCreateRequest {

	private Long userId;
}
