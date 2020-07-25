package com.htp.controller.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrorMessage {
	private Long errorCode;
	private String message;

	public ErrorMessage(String message) {
		this.message = message;
	}
}