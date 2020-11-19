package com.master.salmonapp.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.FetchType;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Where;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "Master_Team")
@Where(clause = "status = 'ACTIVE'")
public class Team extends Persistence {
    private static final long serialVersionUID = -1079959320014409414L;
    
    @NotNull
    @Column(name="nama_team")
    private String teamName;

    @NotNull
    @Column(name="create_date")
    private Date createDate;

    @Column(name = "active", length = 2)
	private Integer active;

    @JoinColumn(name = "user_id")
    @OneToOne(targetEntity = User.class, fetch = FetchType.LAZY)
    private User user;

    @Where(clause = "status = 'ACTIVE'")
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "team", fetch = FetchType.LAZY)
    private Set<User> users = new HashSet<>();

    @Where(clause = "status = 'ACTIVE'")
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "team", fetch = FetchType.LAZY)
    private Set<TargetTeam> targetTeams;

    @Where(clause = "status = 'ACTIVE'")
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "team", fetch = FetchType.LAZY)
    private Set<TargetSales> targetSales;

    
}
