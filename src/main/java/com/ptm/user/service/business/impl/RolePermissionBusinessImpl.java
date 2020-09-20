package com.ptm.user.service.business.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ptm.user.service.business.RolePermissionBusiness;
import com.ptm.user.service.controller.vo.RolePermissionDTO;
import com.ptm.user.service.enitity.Privilege;
import com.ptm.user.service.enitity.Role;
import com.ptm.user.service.exception.UserDataException;
import com.ptm.user.service.exception.codes.UserDataCode;
import com.ptm.user.service.repository.PrivilegeRepository;
import com.ptm.user.service.repository.RoleRepository;

@Service
public class RolePermissionBusinessImpl implements RolePermissionBusiness {

	@Autowired
	RoleRepository roleRepository;
	@Autowired
	PrivilegeRepository privilegeRepository;

	@Override
	@Transactional(rollbackOn = Exception.class)
	public void addPermission(RolePermissionDTO rolePermissionDTO) {
		Optional<Role> role = roleRepository.findByName(rolePermissionDTO.getRole());

		if (!role.isPresent()) {
			throw new UserDataException(UserDataCode.NO_ROLE_FOUND, UserDataCode.NO_ROLE_FOUND.getErrMsg());
		}

		List<Privilege> listOfPrivilege = privilegeRepository.saveAll(rolePermissionDTO.getPermission().stream()
				.map(m -> new Privilege(m.getPrivilege())).collect(Collectors.toList()));
		Role data = role.get();
		data.setPrivileges(listOfPrivilege);
		roleRepository.save(data);

	}

	@Override
	public void upDatePermission(String permissionName) {

	}

	@Override
	public void deleteRolePermission(String permissionName) {

	}

}
