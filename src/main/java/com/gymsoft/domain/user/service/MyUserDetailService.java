package com.gymsoft.domain.user.service;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.gymsoft.domain.user.auth.AuthUser;
import com.gymsoft.domain.user.dto.UserDTO;
import com.gymsoft.domain.user.entity.Role;
import com.gymsoft.domain.user.entity.User;
import com.gymsoft.domain.user.entity.UserInfo;
import com.gymsoft.domain.user.repository.UserRepository;


@Service("myUserDetailService")
public class MyUserDetailService implements UserDetailsService {

	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder bcryptEncoder;
	
	MyUserDetailService(UserRepository userRepository){
		this.userRepository = userRepository;
	}
	
	
	@Override
	public UserDetails loadUserByUsername(String username) {
		Optional<User> user = userRepository.findByUsername(username);
		if(user.isPresent()) {
			return new AuthUser(user.get());
		}
		throw new UsernameNotFoundException("User not found in system.");
	}
	
	public User registerUser(UserDTO userDto) {
		Objects.requireNonNull(userDto);
		String userEmail = userDto.getEmail();
		Optional<User> userFound = userRepository.findByEmail(userEmail);
		if(userFound.isPresent()) {
			throw new RuntimeException("User already exist with this email");
		}
		
		User user = new User();
		user.setUsername(userDto.getUsername());
		user.setPassword(bcryptEncoder.encode(userDto.getPassword()));
		user.setAccountEnabled("Y");
		user.setAccountExpired("N");
		user.setAccountLocked("N");
		
		user.setEmail(userDto.getEmail());
		UserInfo userInfo = new UserInfo();
		userInfo.setFirstName(userDto.getFirstName());
		userInfo.setLastName(userDto.getLastName());
		userInfo.setNickName(userDto.getNickName());
		userInfo.setBirthDate(userDto.getBirthDate());
		userInfo.setUser(user);
		
		Role role = new Role();
		role.setName("USER");
		
		user.setUserInfo(userInfo);
		user.setUserRoles(Arrays.asList(role));
		
		userRepository.save(user);
		
		return user;
		
	}
	
	public Optional<User> getUser(String username)
	{
		
		return userRepository.findByUsername(username);
		
	}

}
