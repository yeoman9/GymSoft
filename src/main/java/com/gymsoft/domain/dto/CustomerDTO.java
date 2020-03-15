package com.gymsoft.domain.dto;

import java.util.Date;

import lombok.Data;

@Data
public class CustomerDTO {
	private String name;
	private String email;
	private String mobile;
	private Date dateOfJoin;
	private Date lastDate;
	private String gender;
	private String pin;	
}
