package com.gymsoft.domain.controller;


import static com.gymsoft.config.security.Constants.HEADER_STRING;
import static com.gymsoft.config.security.Constants.TOKEN_PREFIX;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gymsoft.commons.CustomSuccessResponse;
import com.gymsoft.config.security.TokenProvider;
import com.gymsoft.domain.dto.UserDTO;
import com.gymsoft.domain.entity.User;
import com.gymsoft.domain.service.MyUserDetailService;

@RestController
@RequestMapping("/apis/v1/users")
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {

	@Autowired
    private MyUserDetailService userService;
	
	@Autowired
	private TokenProvider jwtTokenUtil;
	
	@PostMapping(value="/signup")
    public ResponseEntity<CustomSuccessResponse> create(@RequestBody UserDTO userDto){
         userService.registerUser(userDto);
         CustomSuccessResponse body = new CustomSuccessResponse();
         body.setMessage("User created successfully");
         body.setTimestamp(LocalDateTime.now());
         return new ResponseEntity<>(body, HttpStatus.OK);
    }
	
	@PostMapping(value="/user",produces = MediaType.APPLICATION_JSON_VALUE)
	public Optional<User> getUser(HttpServletRequest req) {
		String header = req.getHeader(HEADER_STRING);
		String username = null;
		String authToken = null;
		if (header != null && header.startsWith(TOKEN_PREFIX)) {
			authToken = header.replace(TOKEN_PREFIX, "");

			username = jwtTokenUtil.getUsernameFromToken(authToken);
		}
		
		return userService.getUser(username);
	}

	
}
