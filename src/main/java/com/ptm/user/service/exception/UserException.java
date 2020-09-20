package com.ptm.user.service.exception;

public class UserException extends BadRequestAlertException {

    private static final long serialVersionUID = 1L;

    public UserException() {
        super(ErrorConstants.LOGIN_ALREADY_USED_TYPE, "user does not exist!", "userManagement", "usernotexists");
    }
}
