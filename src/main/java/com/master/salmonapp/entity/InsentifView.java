package com.master.salmonapp.entity;

import java.math.BigInteger;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import com.master.salmonapp.entity.Persistence.Status;

import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Where;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
// @EntityListeners(AuditingEntityListener.class)
// @DynamicUpdate
// @MappedSuperclass
@Entity
@Table(name = "insentif")
@Where(clause = "status = 'ACTIVE'")
public class InsentifView {

    @JoinColumn(name = "insentif_id")
    @ManyToOne(targetEntity = Insentif.class, fetch = FetchType.LAZY)
    private Insentif insentif;

    @JoinColumn(name = "deal_id")
    @ManyToOne(targetEntity = Deal.class, fetch = FetchType.LAZY)
    private Deal deal;

    @JoinColumn(name = "user_id")
    @ManyToOne(targetEntity = User.class, fetch = FetchType.LAZY)
    private User user;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer id;


    @NotNull
    @Column(name = "insentif")
    private BigInteger vInsentif;

    @NotNull
    @Column(name = "unit", length = 2)
    private Integer unit;

    @NotNull
    @Column(name = "new_insentif")
    private BigInteger newInsentif;

    @NotNull
    @Column(name = "month_insentif", length = 2)
    private String monthInsentif;

    @NotNull
    @Column(name = "year_insentif", length = 4)
    private String yearInsentif;

    @Column(length = 50)
    @CreatedBy
    private String createdBy;

    @Column(length = 50)
    @LastModifiedBy
    private String updatedBy;

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    private Date createdTime;

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    @LastModifiedDate
    private Date updatedTime;

    @Column(length = 50)
    @Enumerated(EnumType.STRING)
    private Status status;

    
    @PrePersist
    public void prePersist() {
        setCreatedTime(new Date());
        setUpdatedTime(new Date());
        setStatus(Status.ACTIVE);
        setCreatedBy("system");
    }

    @PreUpdate
    public void preUpdate() {
        setUpdatedTime(new Date());
        setUpdatedBy("system");
    }
 
}
