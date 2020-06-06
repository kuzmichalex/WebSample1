package com.htp.domain;

import java.sql.Date;
import java.util.Objects;

/**
 * class that reflects m_roles table entries
 * */
public class User {

	private long id;
	private String login;
	private String name;
	private Date birthDate;
	private String password;

	// Constructor
	public User() {
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, login, name, birthDate, login, password);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null || getClass() != obj.getClass()) return false;
		User user = (User) obj;
		return Objects.equals(id, user.id) &&
				Objects.equals(login, user.login) &&
				Objects.equals(name, user.name) &&
				Objects.equals(birthDate, user.birthDate) &&
				Objects.equals(password, user.password);
	}

	@Override
	public String toString() {
		return "User{" +
				"id=" + id +
				", login='" + login + '\'' +
				", name='" + name + '\'' +
				", birthDate=" + birthDate +
				", password='" + password + '\'' +
				'}';
	}
}
