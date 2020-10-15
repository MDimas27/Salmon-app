package com.master.salmonapp.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserModel extends PersistenceModel {
    private String username;
	private String fullName;
	private String email;
    private String phone;
    // private String address;
}