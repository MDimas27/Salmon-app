package com.master.salmonapp.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.ManyToMany;


import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Where;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;



@Getter
@Setter
@Entity
@Table(name = "user")
@Where(clause = "status = 'ACTIVE'")
public class User extends Persistence implements Serializable {
    private static final long serialVersionUID = 4457669404205697511L;

    @NotNull
    @Size(min = 5, max = 50)
    @Column(length = 50, unique = true)
    private String username;

    @JsonIgnore
	@NotNull
	@Size(min = 8)
	@Column
	private String password;

	@NotNull
	@Column(name="nama", length = 100)
    private String fullName;
	
	@NotNull
    @Column(length = 50)
	private String email;

	@NotNull
	@Column(length = 15)
	private String jk;

	@NotNull
	@Column(name = "alamat", length = 100)
	private String address;


	@NotNull
	@Column(name = "telepon", length = 25)
	private String phone;

	@NotNull
	@Column(name = "create_date", length = 25)
	private Date createDate;
	
	@Column(name = "active", length = 2)
	private Integer active;

    
	@JsonIgnore
	@ManyToMany
	@JoinTable(name = "user_role", joinColumns = {
			@JoinColumn(name = "username", referencedColumnName = "username") }, inverseJoinColumns = {
					@JoinColumn(name = "role_name", referencedColumnName = "role_name") })
	private Set<Role> roles = new HashSet<>();

	@JoinColumn(name = "user_categori_id")
	@ManyToOne(targetEntity = UserCategory.class, fetch = FetchType.LAZY)
	private UserCategory userCategory;

	@JoinColumn(name = "team_id")
	@ManyToOne(targetEntity = Team.class, fetch = FetchType.LAZY)
	private Team team;

	


}
