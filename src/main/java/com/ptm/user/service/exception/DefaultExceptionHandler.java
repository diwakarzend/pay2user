package com.ptm.user.service.exception;
import java.util.Collection;
import java.util.Map;

import javax.validation.ConstraintViolationException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.ptm.user.service.util.ExceptionUtil;
import com.ptm.user.service.util.FieldError;





/**
 * Exception handlers
 * 
 * 
 */
@RestControllerAdvice
@RequestMapping(produces = "application/json")
public class DefaultExceptionHandler {
	
    private final Log log = LogFactory.getLog(getClass());

    
	/**
	 * Handles constraint violation exceptions
	 * 
	 * @param ex the exception
	 * @return the error response
	 */
    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(value = HttpStatus.OK)
    public Map<String, Object> handleConstraintViolationException(ConstraintViolationException ex) {
    	
		Collection<FieldError> errors = FieldError.getErrors(ex.getConstraintViolations());
		
		return ExceptionUtil.mapOf("exception", "ConstraintViolationException", "errors", errors);
    }
	
    /**
	 * Handles user data exceptions
	 * 
	 * @param ex the exception
	 * @return the error response
	 */
    @ExceptionHandler(UserDataException.class)
    @ResponseStatus(value = HttpStatus.OK)
    public Map<String, Object> handleUserDataException(UserDataException exception) {
        log.warn("UserDataException:", exception);        
		return ExceptionUtil.mapOf("exception", exception.getMessage(), "code", exception.getUserDataCode().getErrCode());
    }
    
}
