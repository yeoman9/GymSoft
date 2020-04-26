package com.gymsoft.commons.aspects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Component
@Aspect
public class PerformanceAspect{

	Logger logger = LoggerFactory.getLogger(PerformanceAspect.class);

	@Around("SystemArchitecture.repository()")
	public Object trace(ProceedingJoinPoint proceedingJP) throws Throwable {
		String methodInformation = proceedingJP.getStaticPart().getSignature().toString();
		StopWatch stopWatch = new StopWatch(methodInformation);
		stopWatch.start();
		
		try {
			return proceedingJP.proceed();
		} finally {
			stopWatch.stop();
			logger.trace(stopWatch.shortSummary());
		}
	}

}
