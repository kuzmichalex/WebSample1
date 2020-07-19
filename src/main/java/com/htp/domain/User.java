package com.htp.domain;

import lombok.Data;

import java.sql.Date;

@Data
public class User {

	private long id;
	private String login;
	private String name;
	private Date birthDate;
	private String password;
}