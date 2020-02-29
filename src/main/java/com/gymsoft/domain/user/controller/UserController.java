package com.gymsoft.domain.user.controller;


import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gymsoft.commons.error.CustomSuccessResponse;
import com.gymsoft.domain.user.dto.UserDTO;
import com.gymsoft.domain.user.service.MyUserDetailService;

@RestController
@RequestMapping("/apis/v1/users")
public class UserController {

	@Autowired
    private MyUserDetailService userService;
	
	@PostMapping(value="/signup")
    public ResponseEntity<CustomSuccessResponse> create(@RequestBody UserDTO userDto){
         userService.registerUser(userDto);
         CustomSuccessResponse body = new CustomSuccessResponse();
         body.setMessage("User created successfully");
         body.setTimestamp(LocalDateTime.now());
         return new ResponseEntity<>(body, HttpStatus.OK);
    }

	
}
