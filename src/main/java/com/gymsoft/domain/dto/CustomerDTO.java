package com.gymsoft.domain.dto;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class CustomerDTO {
	private String name;
	private String email;
	private String mobile;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date dateOfJoin;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date lastDate;
	private String gender;
	private String pin;	
	private MultipartFile avatar;
	private MultipartFile docImage;
	private String kycType;
	private String docNumber;	
}
