package com.htp.domain;

import java.util.Objects;

public class Role {
  private long id;
  private String roleName;

  public long getId() {
    return id;
  }
  public void setId(long id) {
    this.id = id;
  }

  public String getRoleName() {
    return roleName;
  }

  public void setRoleName(String roleName) {
    this.roleName = roleName;
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, roleName);
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) return true;
    if (obj == null || getClass() != obj.getClass()) return false;
    Role role = (Role) obj;
    return Objects.equals(id, role.id) &&
            Objects.equals(roleName, role.roleName);
  }

  @Override
  public String toString() {
    return "Role{" + "id=" + id + ", roleName='" + roleName + '\'' + '}';
  }
}
