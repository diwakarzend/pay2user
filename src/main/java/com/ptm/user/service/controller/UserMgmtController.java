package com.ptm.user.service.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserMgmtController {
	/**
	 * testing
	 * @return
	 */
	
	@GetMapping
	public String register() {
		
		return "hi";
	}

}
