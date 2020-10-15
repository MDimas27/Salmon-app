package com.master.salmonapp.model;

import java.math.BigDecimal;
import java.time.Year;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class TargetModel extends PersistenceModel {

    private TeamModel teamModel;

    private Year tahun;

    private BigDecimal targetT;


}
