package com.master.salmonapp.model;

import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class TeamModel extends PersistenceModel{

   @NotBlank
   private String teamName;

   @NotBlank
   private Date createDate;

   @NotBlank
   private Integer active;

}
