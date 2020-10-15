package com.master.salmonapp.entity;

import java.math.BigDecimal;
import java.time.Year;
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
@Table(name = "target")
@Where(clause = "status = 'ACTIVE'")
public class Target extends Persistence {
    private static final long serialVersionUID = 1130010943968579177L;

    @JoinColumn(name = "team_id")
    @ManyToOne(targetEntity = Team.class, fetch = FetchType.LAZY)
    private Team team;

    @Where(clause = "status = 'ACTIVE'")
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "target", fetch = FetchType.LAZY)
    private Set<TargetDetail> targetDetails;

    @NotNull
    private Year tahun;

    @NotNull
    @Column(name = "Target")
    private BigDecimal targetT;


    
}
