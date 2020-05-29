package com.gymsoft.commons;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomGlobalExceptionHandler extends ResponseEntityExceptionHandler
{

    @ExceptionHandler( RuntimeException.class )
    public ResponseEntity<CustomErrorResponse> customHandleNotFound( Exception ex, WebRequest request )
    {

        CustomErrorResponse errors = new CustomErrorResponse();
        errors.setTimestamp( LocalDateTime.now() );
        errors.setError( ex.getMessage() );
        errors.setStatus( HttpStatus.NOT_FOUND.value() );

        return new ResponseEntity<>( errors, HttpStatus.NOT_FOUND );

    }

    @ExceptionHandler( ConstraintViolationException.class )
    public void constraintViolationException( HttpServletResponse response ) throws IOException
    {
        response.sendError( HttpStatus.BAD_REQUEST.value() );
    }

    // error handle for @Valid
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid( MethodArgumentNotValidException ex,
        HttpHeaders headers, HttpStatus status, WebRequest request )
    {

        Map<String, Object> body = new LinkedHashMap<>();
        body.put( "timestamp", new Date() );
        body.put( "status", status.value() );

        // Get all fields errors
        List<String> errors =
            ex.getBindingResult().getFieldErrors().stream().map( x -> x.getDefaultMessage() ).collect( Collectors.toList() );

        body.put( "errors", errors );

        return new ResponseEntity<>( body, headers, status );

    }
}