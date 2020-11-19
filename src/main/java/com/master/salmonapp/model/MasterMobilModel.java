package com.master.salmonapp.model;

import java.math.BigInteger;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class MasterMobilModel extends PersistenceModel {

    private String namaMobil;

    private String type;

    private String tahunProduksi;

    private BigInteger hargaOtr;

    private Date createDate;

    private Integer active;


    
}
