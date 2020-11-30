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
@Table(name = "Master_Insentif")
@Where(clause = "status = 'ACTIVE'")
public class Insentif extends Persistence{

    public enum TypePayment {
        CASH, CREDIT
    }

    @JoinColumn(name = "mobil_id")
    @ManyToOne(targetEntity = MasterMobil.class, fetch = FetchType.LAZY)
    private MasterMobil masterMobil;

    // @NotNull
    // @Column(name = "revenue")
    // private BigInteger revenue;

    // @NotNull
    // @Column(name = "target_sales")
    // private BigInteger targetDetail;

    @NotNull
    @Column(name = "insentif")
    private BigInteger insentif;

    @NotNull
    @Column(name = "create_date")
    private Date createDate;

    @Column(name = "active", length = 2)
	private Integer active;

    
}
