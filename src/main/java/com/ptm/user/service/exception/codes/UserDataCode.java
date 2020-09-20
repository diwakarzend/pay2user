package com.ptm.user.service.exception.codes;

public enum UserDataCode {
	NO_ROLE_FOUND("ERR1000","No role found : {0} .");
	
    private final String errCode;
    private final String errMsg;

    /**
     * @param text
     */
    private UserDataCode(final String errCode,final String errMsg) {
        this.errCode = errCode;
        this.errMsg = errMsg;
    }

    /**
	 * Return a string representation of this status code.
	 */
	@Override
	public String toString() {
		return  errCode + ": " + errMsg;
	}

	/**
	 * Return the enum constant of this type with the specified numeric value.
	 * 
	 * @param statusCode
	 *            the numeric value of the enum to be returned
	 * @return the enum constant with the specified numeric value
	 * @throws IllegalArgumentException
	 *             if this enum has no constant for the specified numeric value
	 */

	public String getErrCode() {
		return errCode;
	}

	public String getErrMsg() {
		return errMsg;
	}
}