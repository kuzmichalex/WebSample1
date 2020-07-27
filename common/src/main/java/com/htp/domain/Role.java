package com.htp.domain;

import lombok.*;


/**
 * class that reflects m_roles table entries
 */
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Role {
	private long id;
	private String roleName;
	private boolean isDeleted;
}
