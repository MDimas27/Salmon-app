package com.master.salmonapp.model;

import java.math.BigInteger;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class TargetSalesModel extends PersistenceModel{

    private TargetTeamModel targetTeamModel;

    private String tahun;

    private BigInteger targetSales;

    private Date createDate;

    private Integer active;
    
}
