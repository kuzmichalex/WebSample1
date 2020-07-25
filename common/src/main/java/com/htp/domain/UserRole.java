package com.htp.domain;

import java.util.Objects;

/**
 * class that reflects l_user_roles table entries
 * */
public class UserRole {
	private long id;
	private long userId;
	private long roleId;

	public UserRole() {
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public long getRoleId() {
		return roleId;
	}

	public void setRoleId(long roleId) {
		this.roleId = roleId;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, userId, roleId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null || getClass() != obj.getClass()) return false;
		UserRole userRole = (UserRole) obj;
		return Objects.equals(id, userRole.id) &&
				Objects.equals(userId, userRole.userId) &&
				Objects.equals(roleId, userRole.roleId);
	}

	@Override
	public String toString() {
		return "UserRoles{" +
				"id=" + id +
				", user_id=" + userId +
				", role_id=" + roleId +
				'}';
	}
}

