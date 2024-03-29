package com.gymsoft.domain.controller;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.gymsoft.commons.CustomErrorResponse;
import com.gymsoft.config.security.TokenProvider;
import com.gymsoft.domain.auth.AuthUser;
import com.gymsoft.domain.auth.LoginUser;
import com.gymsoft.domain.entity.User;
import com.gymsoft.domain.service.MyUserDetailService;

@CrossOrigin( origins = "*", maxAge = 3600 )
@RestController
@RequestMapping( "/token" )
public class AuthenticationController
{

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenProvider jwtTokenUtil;

    @Autowired
    private MyUserDetailService userService;

    @RequestMapping( value = "/generate-token", method = RequestMethod.POST )
    public ResponseEntity<?> generateToken( @RequestBody LoginUser loginUser )
    {

        try
        {
            final Authentication authentication =
                authenticationManager.authenticate( new UsernamePasswordAuthenticationToken( loginUser.getUsername(),
                                                                                             loginUser.getPassword() ) );
            SecurityContextHolder.getContext().setAuthentication( authentication );
            final String token = jwtTokenUtil.generateToken( authentication );
            
            Optional<User> userOptional = userService.getUser(loginUser.getUsername());
            if( userOptional.isPresent() ) 
            {
            	AuthUser authUser = new AuthUser( userOptional.get() );
            	authUser.setToken( token );
            	return ResponseEntity.ok( authUser );
            }
            
            return new ResponseEntity<>( "Please provide valid credentials", HttpStatus.UNAUTHORIZED );
            
        }
        catch( BadCredentialsException e )
        {

            CustomErrorResponse error = new CustomErrorResponse();
            error.setTimestamp( LocalDateTime.now() );
            error.setError( "The user name or password is incorrect." );
            error.setStatus( HttpStatus.UNAUTHORIZED.value() );

            return new ResponseEntity<>( error, HttpStatus.UNAUTHORIZED );
        }
    }

}
