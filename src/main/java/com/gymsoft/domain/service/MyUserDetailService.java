package com.gymsoft.domain.service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.gymsoft.domain.auth.AuthUser;
import com.gymsoft.domain.dto.UserDTO;
import com.gymsoft.domain.entity.Role;
import com.gymsoft.domain.entity.User;
import com.gymsoft.domain.entity.UserInfo;
import com.gymsoft.domain.repository.RoleRepository;
import com.gymsoft.domain.repository.UserRepository;

@Service( "myUserDetailService" )
public class MyUserDetailService implements UserDetailsService
{

	@Autowired
    private UserRepository userRepository;
	
	@Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder bcryptEncoder;

    @Override
    public UserDetails loadUserByUsername( String username )
    {
        Optional<User> user = userRepository.findByUsername( username );
        if( user.isPresent() )
        {
            return new AuthUser( user.get() );
        }
        throw new UsernameNotFoundException( "User not found in system." );
    }

    public User registerUser( UserDTO userDto )
    {
        Objects.requireNonNull( userDto );
        String userEmail = userDto.getEmail();
        Optional<User> userFound = userRepository.findByEmail( userEmail );
        if( userFound.isPresent() )
        {
            throw new RuntimeException( "User already exist with this email" );
        }

        User user = new User();
        user.setUsername( userDto.getUsername() );
        user.setPassword( bcryptEncoder.encode( userDto.getPassword() ) );
        user.setAccountEnabled( "Y" );
        user.setAccountExpired( "N" );
        user.setAccountLocked( "N" );
        user.setEmail( userDto.getEmail() );
        
        UserInfo userInfo = new UserInfo();
        userInfo.setFirstName( userDto.getFirstName() );
        userInfo.setLastName( userDto.getLastName() );
        userInfo.setNickName( userDto.getNickName() );
        userInfo.setBirthDate( userDto.getBirthDate() );
        userInfo.setUser( user );
        
        Set<Role> roles = new HashSet<Role>();
        for( Role role : userDto.getRoles() ) 
        {
        	Optional<Role> tempRrole = roleRepository.findByName( role.getName() );
        	if( tempRrole.isPresent() ) 
        	{
        		roles.add( tempRrole.get() );
        	}
        }

        user.setUserInfo( userInfo );
        user.setUserRoles( roles );

        userRepository.save( user );

        return user;

    }

    public Optional<User> getUser( String username )
    {
        return userRepository.findByUsername( username );
    }

}
