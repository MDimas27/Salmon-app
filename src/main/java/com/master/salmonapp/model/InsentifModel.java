package com.master.salmonapp.model;

import java.math.BigInteger;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.master.salmonapp.entity.TargetSales;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class InsentifModel extends PersistenceModel {

    // private TargetSales targetSales;

    // private BigInteger revenue;

    // private BigInteger targetDetail;

    private BigInteger insentif;

    private Date createDate;

    private Integer active;
    
}
