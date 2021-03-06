package com.master.salmonapp.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
@Entity
@Table(name = "role")
public class Role {

	public enum RoleName {
		ROLE_ADMIN, ROLE_USER, ROLE_CLIENT, ROLE_CFO, ROLE_HR, ROLE_SALESLEAD
	}

	@Id
	@NotNull
	@Size(min = 0, max = 50)
	@Column(name = "role_name", length = 50)
	private String roleName;

}
