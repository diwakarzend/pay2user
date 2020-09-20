package com.ptm.user.service.exception;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class MobeGramException extends RuntimeException  {
	private static final long serialVersionUID = 1L;
	private final Logger log = LoggerFactory.getLogger(this.getClass());
    private final String exception;
    
    private final String exceptionMessage;

    private final List<String> paramList = new ArrayList<>();
    public MobeGramException(String exception, String exceptionMessage, String... params) {
        super(exception);
        this.exception = exception;
        this.exceptionMessage = exceptionMessage;
        if (params != null && params.length > 0) {
            for (int i = 0; i < params.length; i++) {
                paramList.add(params[i]);
            }
        }
    }

    
    public MobeGramException(String exception, String exceptionMessage, Exception detailedException,String... params) {
		super(exception);
		this.exception = exception;
		this.exceptionMessage = exceptionMessage;
		log.info("Exception  {}",detailedException);
		if (params != null && params.length > 0) {
			for (int i = 0; i < params.length; i++) {
				paramList.add(params[i]);
			}
		}
	}
    public MobeGramException(String exception,String exceptionMessage, List<String> paramList) {
        super(exception);
        this.exception = exception;
        this.exceptionMessage = exceptionMessage;
        this.paramList.addAll(paramList);
    }

    public ParameterizedErrorVM getErrorVM() {
        return new ParameterizedErrorVM(exception, exceptionMessage, paramList);
    }
    
}
