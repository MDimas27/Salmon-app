package com.master.salmonapp.model;

import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserCategoryRequestCreateModel {

    @NotBlank
    private String name;

    @NotBlank
    private String code;

}