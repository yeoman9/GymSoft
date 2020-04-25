package com.gymsoft.domain.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.Email;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name="customer")
public class Customer {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String name;
	private String mobile;
	private String gender;
	
	@Column(unique = true)
	private String pin;

	@Email
	@Column(name = "email")
	private String email;

	@Temporal(TemporalType.DATE)
	private Date dateOfJoin;
	
	@Temporal(TemporalType.DATE)
	private Date lastDate;
	
	private String avatar;
	private String docImage;
	private String kycType;
	private String docNumber;
	
	@Transient
    public boolean isActive() {
        return lastDate.after(new Date());
    }

}
