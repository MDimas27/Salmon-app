package com.master.salmonapp.model;

import java.util.Date;

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
    private String jk;
    private String address;
    private String phone;
    private Date createDate;
    private Integer active;
    
}