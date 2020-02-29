package com.gymsoft.domain.user.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name="users_details")
public class UserInfo {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="detail_id")
	private Long detailId;
	
	@Column(name="user_id")
	private Long userId;
	
	@Column(name="first_name")
	private String firstName;
	
	@Column(name="last_name")
	private String lastName;
	
	@Column(name="nick_name")
	private String nickName;
	
	
	@Column(name="profile_image")
	private String profileImage;
	
	@Temporal(TemporalType.DATE)
	@Column(name="dob")
	private Date birthDate;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="creation_date", updatable = false)
	private Date creationDate;
	
	@Temporal(TemporalType.DATE)
	@Column(name="effective_from")
	private Date effectiveFrom;
	
	@Temporal(TemporalType.DATE)
	@Column(name="effective_to")
	private Date effectiveTo;
}
