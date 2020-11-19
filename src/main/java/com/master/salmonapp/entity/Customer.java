package com.master.salmonapp.entity;

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
@Table(name = "customer")
@Where(clause = "status = 'ACTIVE'")
public class Customer extends Persistence{

    @NotNull
    @Column(name = "nama_customer", length = 100)
    private String nama;

    @NotNull
    @Column(name = "telepon", length = 20)
    private String phone;

    @NotNull
    @Column(name = "email_customer", length = 100)
    private String email;

    @NotNull
    @Column(length = 15)
    private String jk;

    @NotNull
	@Column(name = "alamat", length = 100)
    private String address;

    @NotNull
    @Column(name = "create_date")
    private Date createDate;

    @Column(name = "active", length = 2)
	private Integer active;
    
    
    @Where(clause = "status = 'ACTIVE'")
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "customer", fetch = FetchType.LAZY)
    private Set<Deal> deals;

    @Where(clause = "status = 'ACTIVE'")
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "customer", fetch = FetchType.LAZY)
    private Set<Prospek> prospeks;

}
