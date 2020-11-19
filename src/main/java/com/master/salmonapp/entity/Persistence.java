package com.master.salmonapp.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Data;

@Data
@EntityListeners(AuditingEntityListener.class)
@DynamicUpdate
@MappedSuperclass
public class Persistence implements Serializable {
    private static final long serialVersionUID = -3268940466026097783L;

    public enum Status {
        ACTIVE, NOT_ACTIVE 
    }

    @Id
    // @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="UUID", unique = true, length = 36, nullable=false)
    private String id;

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
        this.id = UUID.randomUUID().toString();
    }

    @PreUpdate
    public void preUpdate() {
        setUpdatedTime(new Date());
        setUpdatedBy("system");
    }
}