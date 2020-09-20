package com.ptm.user.service.business;

import com.ptm.user.service.config.CustomUserDetails;
import com.ptm.user.service.controller.dto.FileBucketDTO;

public interface KycBusiness {
	
	public void uploadFile(FileBucketDTO fileBucket,CustomUserDetails customUserDetails);

}
