package com.master.salmonapp.model;

import java.util.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CustomerRequestModel {

    @NotBlank
    private String nama;

    @NotBlank
    private String phone;

    @NotBlank
    private String email;

    @NotBlank
    private String jk;

    @NotBlank
    private String address;

    @NotBlank
    private Date createDate;

    @NotBlank
    private Integer active;

    
}
