package com.master.salmonapp.entity;

import java.math.BigInteger;
import java.time.Year;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Where;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "Target_Team")
@Where(clause = "status = 'ACTIVE'")
public class TargetTeam extends Persistence {
    private static final long serialVersionUID = 1130010943968579177L;

    @NotNull
    @Column(name="year_target", length = 4)
    private String tahun;

    @NotNull
    @Column(name = "target")
    private BigInteger targetTeam;

    @NotNull
    @Column(name = "create_date")
    private Date createDate;

    @Column(name = "active", length = 2)
	private Integer active;

    @JoinColumn(name = "team_id")
    @ManyToOne(targetEntity = Team.class, fetch = FetchType.LAZY)
    private Team team;

    @Where(clause = "status = 'ACTIVE'")
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "targetTeam", fetch = FetchType.LAZY)
    private Set<TargetSales> targetSales;



    
}
