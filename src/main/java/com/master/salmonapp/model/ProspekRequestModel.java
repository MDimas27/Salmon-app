package com.master.salmonapp.model;

import java.math.BigInteger;
import java.util.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProspekRequestModel {
    
    // @NotNull
    // private String id;

    @NotBlank
    private String keterangan;

    // @NotBlank
    // private String stage;

    // @NotBlank
    // private String closingStatus;

    // @NotBlank
    // private BigInteger prospekRevenue;

    @NotBlank
    private Date prospekDate;

    @NotBlank
    private Date createDate;

    @NotBlank
    private Integer active;
}
