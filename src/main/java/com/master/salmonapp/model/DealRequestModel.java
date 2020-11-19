package com.master.salmonapp.model;

import java.math.BigInteger;
import java.util.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class DealRequestModel {

    // @NotNull
    // private String id;
    
    @NotBlank
    private String keterangan;

    @NotBlank
    private String stage;

    @NotBlank
    private BigInteger revenue;

    @NotBlank
    private Date createDate;
    
    @NotBlank
    private Integer active;
}
