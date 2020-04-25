package com.gymsoft.domain.user.events;

import org.springframework.context.ApplicationEvent;

import com.gymsoft.domain.user.entity.User;

public class RegistrationCompleted extends ApplicationEvent {

	private final User user;
	private final String appUrl;
	public RegistrationCompleted(final User user,final String appUrl) {
		super(user);
		this.user = user;
		this.appUrl = appUrl;
	}

	public String getAppUrl() {
        return appUrl;
    }

    public User getUser() {
        return user;
    }

	
}
