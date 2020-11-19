package com.master.salmonapp.model;

import java.util.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class TeamRequestModel{

  
    @NotBlank
    private String teamName;
    
    @NotBlank
    private Date createDate;

    @NotBlank
    private Integer active;

}

