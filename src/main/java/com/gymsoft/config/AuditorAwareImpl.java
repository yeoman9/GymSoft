package com.gymsoft.config;

import java.util.Optional;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.gymsoft.domain.auth.AuthUser;

public class AuditorAwareImpl implements AuditorAware<Long> {

	@Override
	public Optional<Long> getCurrentAuditor() {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if( auth != null )
        {
            AuthUser authUser = (AuthUser)auth.getPrincipal();
            if( authUser != null )
            {
                long userId = authUser.getUserId();
                return Optional.ofNullable( userId );
            }
        }

        return Optional.ofNullable( -1L );
	}

}
