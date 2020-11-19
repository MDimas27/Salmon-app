package com.master.salmonapp.model;

import java.math.BigInteger;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class TargetTeamModel extends PersistenceModel {

    private TeamModel teamModel;

    private String tahun;

    private BigInteger targetTeam;

    private Date createDate;

    private Integer active;

}
