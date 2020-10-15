package com.master.salmonapp.model;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class TargetRequestUpdateModel extends TargetRequestCreateModel {

    @NotNull
    private String id;
    
}
