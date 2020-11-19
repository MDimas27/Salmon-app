package com.master.salmonapp.model;

import java.math.BigInteger;
import java.util.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class TargetTeamRequestModel {

    // @NotNull
    // private String id;

    @NotBlank
    private String tahun;

    @NotBlank
    private BigInteger targetTeam;

    @NotBlank
    private Date createDate;

    @NotBlank
    private Integer active;
    
}
