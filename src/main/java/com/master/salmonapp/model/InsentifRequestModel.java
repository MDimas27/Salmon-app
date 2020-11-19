package com.master.salmonapp.model;

import java.math.BigInteger;
import java.util.Date;

import javax.validation.constraints.NotBlank;

public class InsentifRequestModel {
    
    // @NotNull
    // private String id;
   
    @NotBlank
    private BigInteger revenue;

    @NotBlank
    private BigInteger targetDetail;

    @NotBlank
    private BigInteger insentif;

    @NotBlank
    private Date createDate;

    @NotBlank
    private Integer active;
}
