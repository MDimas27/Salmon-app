package com.master.salmonapp.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class CustomerModel extends PersistenceModel {

    private String nama;

    private String phone;

    private String email;

    private String jk;

    private String address;

    private Date createDate;

	private Integer active;

}
