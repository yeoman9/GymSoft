package com.gymsoft.commons.aspects;

import org.aspectj.lang.annotation.Pointcut;

public class SystemArchitecture {
	
	@Pointcut("execution(* (@org.springframework.stereotype.Repository *).*(..))")
	public void repository() {
		
	}

	@Pointcut("execution(* (@org.springframework.stereotype.Service *).*(..))")
	public void service() {
		
	}
}
