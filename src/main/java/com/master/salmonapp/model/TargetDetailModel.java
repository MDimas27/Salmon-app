package com.master.salmonapp.model;

import java.math.BigDecimal;
import java.time.Year;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class TargetDetailModel extends PersistenceModel{

    private TargetModel targetModel;

    private Year tahun;

    private BigDecimal targetTd;
    
}
