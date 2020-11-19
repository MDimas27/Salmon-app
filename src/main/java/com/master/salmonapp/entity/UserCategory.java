package com.master.salmonapp.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;


import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import org.hibernate.annotations.Where;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "user_category")
@Where(clause = "status = 'ACTIVE'")
public class UserCategory extends Persistence implements Serializable{
    private static final long serialVersionUID = 3626318616512842601L;

    @Column(length = 100)
    private String name;

    @Column(length = 50)
    private String code;

    @Where(clause = "status = 'ACTIVE'")
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userCategory", fetch = FetchType.LAZY)
    private Set<User> users = new HashSet<>();

}
