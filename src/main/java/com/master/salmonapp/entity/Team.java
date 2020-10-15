package com.master.salmonapp.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.FetchType;
import javax.persistence.Table;

import org.hibernate.annotations.Where;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "team")
@Where(clause = "status = 'ACTIVE'")
public class Team extends Persistence {
    private static final long serialVersionUID = -1079959320014409414L;

    @JoinColumn(name = "user_id")
    @OneToOne(targetEntity = User.class, fetch = FetchType.LAZY)
    private User user;

    @Where(clause = "status = 'ACTIVE'")
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "team", fetch = FetchType.LAZY)
    private Set<Target> targets;
 
}
