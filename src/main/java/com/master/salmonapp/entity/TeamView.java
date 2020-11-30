package com.master.salmonapp.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import com.master.salmonapp.entity.Persistence.Status;

import org.hibernate.annotations.Where;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "team")
@Where(clause = "status = 'ACTIVE'")
public class TeamView {

    @JoinColumn(name = "team_id")
    @ManyToOne(targetEntity = Team.class, fetch = FetchType.LAZY)
    private Team team;

    @JoinColumn(name = "user_id")
    @ManyToOne(targetEntity = User.class, fetch = FetchType.LAZY)
    private User user;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer id;

    @NotNull
    @Column(name = "create_date")
    private Date createDate;

    @Column(name = "active", length = 2)
    private Integer active;
    
    // @Column(length = 50)
    // @CreatedBy
    // private String createdBy;

    // @Column(length = 50)
    // @LastModifiedBy
    // private String updatedBy;

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    private Date createdTime;

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    @LastModifiedDate
    private Date updatedTime;

    // @Column(length = 50)
    // @Enumerated(EnumType.STRING)
    // private Status status;


    @PrePersist
    public void prePersist() {
        setCreatedTime(new Date());
        setUpdatedTime(new Date());
        // setStatus(Status.ACTIVE);
        // setCreatedBy("system");
    }

    @PreUpdate
    public void preUpdate() {
        setUpdatedTime(new Date());
        // setUpdatedBy("system");
    }
    
}
