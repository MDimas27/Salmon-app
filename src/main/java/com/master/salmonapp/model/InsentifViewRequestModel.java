package com.master.salmonapp.model;

import java.math.BigInteger;
import java.util.Date;

import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class InsentifViewRequestModel {

    @NotBlank
    private BigInteger vInsentif;

    @NotBlank
    private Integer unit;

    @NotBlank
    private BigInteger newInsentif;

    @NotBlank
    private String monthInsentif;

    @NotBlank
    private String yearInsentif;


    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss", timezone = "GMT+7")
    private Date createdTime;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss", timezone = "GMT+7")
    private Date updatedTime;
    
}
