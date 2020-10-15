package com.master.salmonapp.entity;

import java.math.BigDecimal;
import java.time.Year;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Where;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "target_detail")
@Where(clause = "status = 'ACTIVE'")
public class TargetDetail extends Persistence {
    private static final long serialVersionUID = 1130010943968579177L;

    @JoinColumn(name = "target_id")
    @ManyToOne(targetEntity = Target.class, fetch = FetchType.LAZY)
    private Target target;

    @JoinColumn(name = "team_id")
    @ManyToOne(targetEntity = Team.class, fetch = FetchType.LAZY)
    private Team team;

    @NotNull
    private Year tahun;

    @NotNull
    @Column(name = "Target")
    private BigDecimal targetTd;

}


