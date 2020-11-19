package com.master.salmonapp.entity;

import java.io.Serializable;
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
@Table(name = "Master_Mobil")
@Where(clause = "status = 'ACTIVE'")
public class MasterMobil extends Persistence {
    
    // @JoinColumn(name = "prospek_id")
    // @ManyToOne(targetEntity = Prospek.class, fetch = FetchType.LAZY)
    // private Prospek prospek;

    @NotNull
    @Column (name = "nama_mobil", length = 255)
    private String namaMobil;
    
    @NotNull
    @Column (length = 255)
    private String type;

    @NotNull
    @Column(name = "tahun_produksi", length = 4)
    private String tahunProduksi;

    @NotNull
    @Column(name = "harga_otr", length = 20)
    private BigInteger hargaOtr;

    @NotNull
    @Column(name = "create_date")
    private Date createDate;

    @NotNull
    @Column(length = 2)
    private Integer active;

    @Where(clause = "status = 'ACTIVE'")
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "masterMobil", fetch = FetchType.LAZY)
    private Set<Prospek> prospeks;



    
}
