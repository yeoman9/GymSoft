package com.gymsoft.domain.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@NoArgsConstructor
@EqualsAndHashCode(exclude="user")
@ToString(exclude="user")
@Table( name = "users_details" )
public class UserInfo
{
    @Id
    @GeneratedValue( strategy = GenerationType.AUTO )
    @Column( name = "detail_id" )
    private Long detailId;

    @OneToOne
    @MapsId
    @JsonIgnore
    private User user;

    @Column( name = "first_name" )
    private String firstName;

    @Column( name = "last_name" )
    private String lastName;

    @Column( name = "nick_name" )
    private String nickName;

    @Column( name = "profile_image" )
    private String profileImage;

    @Temporal( TemporalType.DATE )
    @Column( name = "dob" )
    private Date birthDate;

    @Temporal( TemporalType.TIMESTAMP )
    @Column( name = "creation_date", updatable = false )
    private Date creationDate;

    @Temporal( TemporalType.DATE )
    @Column( name = "effective_from" )
    private Date effectiveFrom;

    @Temporal( TemporalType.DATE )
    @Column( name = "effective_to" )
    private Date effectiveTo;
}
