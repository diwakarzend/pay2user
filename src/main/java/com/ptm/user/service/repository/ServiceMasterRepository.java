package com.ptm.user.service.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ptm.user.service.enitity.ServiceMaster;

public interface ServiceMasterRepository extends JpaRepository<ServiceMaster, Long> {

	public List<ServiceMaster> findByUserId();

}
