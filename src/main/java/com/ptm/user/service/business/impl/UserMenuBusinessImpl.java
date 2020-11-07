package com.ptm.user.service.business.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ptm.user.service.business.UserMenuBusiness;
import com.ptm.user.service.config.security.SecurityUtils;
import com.ptm.user.service.enitity.MenuMaster;
import com.ptm.user.service.enitity.User;
import com.ptm.user.service.repository.UserRepository;

@Service
public class UserMenuBusinessImpl implements UserMenuBusiness {

	
	@Autowired
	private UserRepository userRepository;
	
	
	@Override
	public List<String> getMenuMasterByUser() {
		Optional<User> user=userRepository.findOneByUsername(SecurityUtils.getCurrentUserLogin().get());
		return user.get().getMenu().stream().map(MenuMaster::getMenuName).collect(Collectors.toList());
	}

}
