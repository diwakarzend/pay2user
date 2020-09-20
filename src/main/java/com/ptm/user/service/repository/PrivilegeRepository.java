package com.ptm.user.service.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ptm.user.service.enitity.Privilege;

public interface PrivilegeRepository extends JpaRepository<Privilege, String>{

}
