package com.gymsoft.domain.user.events;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.core.env.Environment;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import com.gymsoft.domain.user.entity.User;
import com.gymsoft.domain.user.service.MyUserDetailService;

@Component
public class RegistrationCompletionListener implements ApplicationListener<RegistrationCompleted> {

	@Autowired
	private JavaMailSender mailSender;
	
	@Autowired
	private MyUserDetailService service;

	@Autowired
    private Environment env;
	
	@Override
	public void onApplicationEvent(RegistrationCompleted event) {
		this.confirmRegistration(event);
	}

	private void confirmRegistration(final RegistrationCompleted event) {
		final User user = event.getUser();
		final String token = UUID.randomUUID().toString();
		service.createVerificationTokenForUser(user, token);

		final SimpleMailMessage email = constructEmailMessage(event, user, token);
		mailSender.send(email);
	}

	//

	private final SimpleMailMessage constructEmailMessage(final RegistrationCompleted event, final User user,
			final String token) {
		final String recipientAddress = user.getEmail();
		final String subject = "Registration Confirmation";
		final String confirmationUrl = event.getAppUrl() + "/registrationConfirm.html?token=" + token;
		 String message = "Hello %s, \n we are glad to have you in our Gym family.\n Please confirm your account by clicking on link.\n Link %s \n \n Team GymSoft";
		message = String.format(message, (user.getUserInfo().getFirstName()+" "+user.getUserInfo().getLastName()),confirmationUrl);
		 final SimpleMailMessage email = new SimpleMailMessage();
		email.setTo(recipientAddress);
		email.setSubject(subject);
		email.setText(message);
		email.setFrom(env.getProperty("support.email"));
		return email;
	}

}
