package com.master.salmonapp.model;

import java.math.BigInteger;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class DealModel extends PersistenceModel {
    
    private ProspekModel prospekModel;

    private String keterangan;

    // private String stage;

    // private BigInteger revenue;

    private Integer unit;

    private BigInteger newPrice;

    private Date dealDate;

    private Date createDate;

    private Integer active;
}
