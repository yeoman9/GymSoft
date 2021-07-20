package com.gymsoft.domain.entity;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table( name = "users" )
public class User
{

    @Id
    @GeneratedValue( strategy = GenerationType.AUTO )
    private Long id;

    private String username;
    @JsonIgnore
    private String password;
    private String accountLocked;
    private String accountExpired;
    private String accountEnabled;

    @Email
    @Column( name = "email" )
    private String email;

    @Temporal( TemporalType.TIMESTAMP )
    private Date creationDate;

    @OneToOne( fetch = FetchType.LAZY, cascade = CascadeType.ALL )
    @JoinColumn( name = "id" )
    private UserInfo userInfo;

    @ManyToMany( cascade = { CascadeType.MERGE, CascadeType.DETACH }, fetch = FetchType.EAGER )
    @JoinTable( name = "users_roles", joinColumns = @JoinColumn( name = "user_id" ), inverseJoinColumns = @JoinColumn( name = "role_id" ) )
    private Set<Role> userRoles;
    
}
