package com.ptm.user.service.exception;

import java.text.MessageFormat;

import com.ptm.user.service.exception.codes.UserDataCode;




public class UserDataException extends RuntimeException {
	 
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String message ;
    
    private UserDataCode userDataCode;
 
    public UserDataException() {
        super();
    }
 
    public UserDataException(UserDataCode userDataCode,String message) {
        super(MessageFormat.format(userDataCode.getErrMsg(), message));
        this.message = message;
        this.userDataCode = userDataCode;
    }
 
    public UserDataException(Throwable cause) {
        super(cause);
        
    }
    
    public UserDataException(UserDataCode userDataCode,String message,Throwable cause) {
    	super(MessageFormat.format(userDataCode.getErrMsg(), message),cause);
        this.message = message;
        this.userDataCode = userDataCode;
    }
 
    @Override
    public String toString() {
        return MessageFormat.format(userDataCode.getErrMsg(), message);
    }
 
    
    public String getMessage() {
        return message;
    }

	public UserDataCode getUserDataCode() {
		return userDataCode;
	}

	public void setUserDataCode(UserDataCode userDataCode) {
		this.userDataCode = userDataCode;
	}
}