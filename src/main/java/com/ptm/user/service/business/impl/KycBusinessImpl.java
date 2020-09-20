package com.ptm.user.service.business.impl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ptm.user.service.business.KycBusiness;
import com.ptm.user.service.business.mapper.UserKycDtlMapper;
import com.ptm.user.service.config.CustomUserDetails;
import com.ptm.user.service.controller.dto.FileBucketDTO;
import com.ptm.user.service.controller.dto.KycDTO;
import com.ptm.user.service.repository.UserKycDtlRepository;

@Service
public class KycBusinessImpl implements KycBusiness {

	@Autowired
	private UserKycDtlMapper userKycDtlMapper;

	@Autowired
	private UserKycDtlRepository userKycDtlRepository;

	@Transactional
	@Override
	public void uploadFile(FileBucketDTO fileBucket, CustomUserDetails customUserDetails) {

		String loc = "src/main/webapp/content/kyc/" + customUserDetails.getUsername();
		Path path = Paths.get(loc);
		if (!Files.exists(path)) {
			try {
				Files.createDirectories(path);
			} catch (IOException e) {
				// throw esxception
				e.printStackTrace();
			}
		}

		List<KycDTO> kycs=new ArrayList<>();
		fileBucket.getFiles().stream().forEach(file -> {
			try {
				
				// kycs set  here
				Path targetpath = path;
				Files.copy(file.getInputStream(), targetpath.resolve(file.getOriginalFilename()),
						StandardCopyOption.REPLACE_EXISTING);
			} catch (IOException e) {
				// throw esxception
				e.printStackTrace();
			}
		});
		// to do
		userKycDtlRepository.save(userKycDtlMapper.mappKycFile(kycs));

	}

}
