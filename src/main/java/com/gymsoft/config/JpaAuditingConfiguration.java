package com.gymsoft.config;

import java.util.Optional;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.gymsoft.domain.auth.AuthUser;

@Configuration
@EnableJpaAuditing( auditorAwareRef = "auditorProvider" )
public class JpaAuditingConfiguration
{

    @Bean
    public AuditorAware<Long> auditorProvider()
    {
        return new AuditorAwareImpl();
    }
}
