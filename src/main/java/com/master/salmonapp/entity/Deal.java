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
@Table(name = "Deal")
@Where(clause = "status = 'ACTIVE'")
public class Deal extends Persistence {

    public enum TypePayment {
        CASH, CREDIT
    }
    

    @JoinColumn(name = "prospek_id")
    @ManyToOne(targetEntity = Prospek.class, fetch = FetchType.LAZY)
    private Prospek prospek;

    @JoinColumn(name = "customer_id")
	@ManyToOne(targetEntity = Customer.class, fetch = FetchType.LAZY)
    private Customer customer;

    @JoinColumn(name = "user_id")
    @ManyToOne(targetEntity = User.class, fetch = FetchType.LAZY)
    private User user;

    @JoinColumn(name = "mobil_id")
    @ManyToOne(targetEntity = MasterMobil.class, fetch = FetchType.LAZY)
    private MasterMobil masterMobil;


    @NotNull
    @Column(length = 255)
    private String keterangan;

    // @NotNull
    // @Column(length = 30)
    // private String stage;

    @NotNull
    @Column(name = "unit")
    private Integer unit;

    @NotNull
    @Column(name = "new_price")
    private BigInteger newPrice;

    @NotNull
    @Column(name = "deal_date")
    private Date dealDate;

    @NotNull
    @Column(name = "create_date")
    private Date createDate;

    @Column(name = "active", length = 2)
	private Integer active;


}
    
