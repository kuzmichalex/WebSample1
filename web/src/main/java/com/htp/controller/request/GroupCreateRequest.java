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

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ApiModel(description = "Group creation model")
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class GroupCreateRequest {
	@NotNull
	@NotEmpty
	@Size(min = 1, max = 50)
	@ApiModelProperty(required = true, dataType = "string", notes = "Group name")
	private String groupName;

	@NotNull
	@NotEmpty
	@Size(min = 1, max = 200)
	@ApiModelProperty(required = true, dataType = "string", notes = "Role Name")
	private String description;

//	@NotNull
//	@ApiModelProperty(required = false, dataType = "Date", notes = "Date creation. Will be defined automatically, if null")
//	private Date dateFoundation;

//	@NotNull
//	@NotEmpty
//	@ApiModelProperty(required = false, dataType = "Date", notes = "False by default")
//	private HibernateUser userFounder;

}
