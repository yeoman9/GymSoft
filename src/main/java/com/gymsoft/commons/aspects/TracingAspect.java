package com.gymsoft.commons.aspects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class TracingAspect
{

    Logger logger = LoggerFactory.getLogger( TracingAspect.class );

    @Around( "SystemArchitecture.repository() || SystemArchitecture.service()" )
    public Object trace( ProceedingJoinPoint proceedingJP ) throws Throwable
    {

        String methodInformation = proceedingJP.getStaticPart().getSignature().toString();
        logger.trace( "Entering " + methodInformation );

        try
        {
            return proceedingJP.proceed();
        }
        catch( Throwable ex )
        {
            logger.error( "Exception in " + methodInformation, ex );
            throw ex;
        }
        finally
        {
            logger.trace( "Exiting " + methodInformation );
        }
    }

}
