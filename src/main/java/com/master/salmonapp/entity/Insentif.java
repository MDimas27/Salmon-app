package com.master.salmonapp.entity;

import java.math.BigInteger;
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
@Table(name = "Insentif")
@Where(clause = "status = 'ACTIVE'")
public class Insentif extends Persistence{

    @JoinColumn(name = "user_id")
    @ManyToOne(targetEntity = User.class, fetch = FetchType.LAZY)
    private User user;

    @JoinColumn(name = "target_id")
    @ManyToOne(targetEntity = TargetSales.class, fetch = FetchType.LAZY)
    private TargetSales targetSales;

    @NotNull
    @Column(name = "revenue")
    private BigInteger revenue;

    @NotNull
    @Column(name = "target_sales")
    private BigInteger targetDetail;

    @NotNull
    @Column(name = "insentif")
    private BigInteger insentif;

    @NotNull
    @Column(name = "create_date")
    private Date createDate;

    @Column(name = "active", length = 2)
	private Integer active;


    
}
