package com.gymsoft.commons.aspects;

import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class ExceptionLoggingAspect {
	Logger logger = LoggerFactory.getLogger(ExceptionLoggingAspect.class);

	@AfterThrowing(pointcut = "SystemArchitecture.repository()", throwing = "ex")
	public void logDataAccessException(DataAccessException ex) {
		logger.error("Problem in Repositories", ex);
	}

	@AfterThrowing(pointcut = "SystemArchitecture.repository() || SystemArchitecture.service()", throwing = "ex")
	public void logRuntimeException(RuntimeException ex) {
		logger.error("RuntimeException", ex);
	}
}
