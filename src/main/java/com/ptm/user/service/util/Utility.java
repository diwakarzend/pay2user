package com.ptm.user.service.util;

import org.apache.commons.lang3.StringUtils;

import com.ptm.user.service.controller.vo.ManagedUserVM;

public class Utility {
	
	public static boolean checkPasswordLength(String password) {
        return !StringUtils.isEmpty(password) &&
            password.length() >= ManagedUserVM.PASSWORD_MIN_LENGTH &&
            password.length() <= ManagedUserVM.PASSWORD_MAX_LENGTH;
    }

}
