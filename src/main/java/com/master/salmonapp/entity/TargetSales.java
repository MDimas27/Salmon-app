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
@Table(name = "Target_Sales")
@Where(clause = "status = 'ACTIVE'")
public class TargetSales extends Persistence {
    private static final long serialVersionUID = 1130010943968579177L;

    @JoinColumn(name = "target_id")
    @ManyToOne(targetEntity = TargetTeam.class, fetch = FetchType.LAZY)
    private TargetTeam targetTeam;

    @JoinColumn(name = "team_id")
    @ManyToOne(targetEntity = Team.class, fetch = FetchType.LAZY)
    private Team team;

    @JoinColumn(name = "user_id")
    @ManyToOne(targetEntity = User.class, fetch = FetchType.LAZY)
    private User user;

    @NotNull
    @Column(name="year_target", length = 4)
    private String tahun;

    @NotNull
    @Column(name = "Target")
    private BigInteger targetSales;

    @NotNull
    @Column(name = "create_date")
    private Date createDate;

    @Column(name = "active", length = 2)
	private Integer active;

}


