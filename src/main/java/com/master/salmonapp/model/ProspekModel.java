package com.master.salmonapp.model;

import java.math.BigInteger;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProspekModel extends PersistenceModel{

    private CustomerModel customerModel;

    private String keterangan;

    private String stage;

    private String closingStatus;

    private BigInteger prospekRevenue;

    private Date createDate;

    private Integer active;


}
    

