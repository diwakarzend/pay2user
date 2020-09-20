package com.ptm.user.service.business;

import com.ptm.user.service.controller.vo.RolePermissionDTO;

public interface RolePermissionBusiness {
	
	public void addPermission(RolePermissionDTO rolePermissionDTO);
	
	public void upDatePermission(String permissionName);
	public void deleteRolePermission(String permissionName);

}
