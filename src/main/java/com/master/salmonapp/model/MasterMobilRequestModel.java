package com.master.salmonapp.model;

import java.math.BigInteger;
import java.util.Date;

import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class MasterMobilRequestModel {

    @NotBlank
    private String namaMobil;

    @NotBlank
    private String type;

    @NotBlank
    private String tahunProduksi;

    @NotBlank
    private BigInteger hargaOtr;

    @NotBlank
    private Date createDate;

    @NotBlank
    private Integer active;
    
}
