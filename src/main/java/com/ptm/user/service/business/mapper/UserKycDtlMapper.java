package com.ptm.user.service.business.mapper;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ptm.user.service.controller.dto.KycDTO;
import com.ptm.user.service.enitity.UserKycDtl;

@Service
public class UserKycDtlMapper {

	public UserKycDtl mappKycFile(List<KycDTO> kycs) {
		UserKycDtl userKycDtl = new UserKycDtl();
		return userKycDtl;

	}

}
