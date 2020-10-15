package com.master.salmonapp.model;

import java.math.BigDecimal;
import java.time.Year;

import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class TargetDetailRequestCreateModel {

    @NotBlank
    private BigDecimal targetTd;

    @NotBlank
    private Year tahun;
    

    
}
