package com.master.salmonapp.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserCategoryModel extends PersistenceModel {
    
    private String name;

    private String code;
    
}