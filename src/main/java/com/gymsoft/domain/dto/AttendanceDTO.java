package com.gymsoft.domain.dto;

import lombok.Data;

@Data
public class AttendanceDTO {
	private String start;
	private final String rendering = "background";
	private String color;
		
}