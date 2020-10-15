package com.master.salmonapp.model;

import java.math.BigDecimal;
import java.time.Year;

import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class TargetRequestCreateModel {

    @NotBlank
    private BigDecimal targetT;

    @NotBlank
    private Year tahun;
    
}
