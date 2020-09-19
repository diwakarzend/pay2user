package com.ptm.user.service.enitity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="proof_doc_type")
public class ProofDocType {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="proof_doc_type_id")
	private byte proofDocTypeId;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="created_ts")
	private Date createdTs;

	@Column(name="created_user")
	private String createdUser;

	@Column(name="is_active")
	private String isActive;

	@Column(name="max_size_allowed_in_mb")
	private int maxSizeAllowedInMb;

	@Column(name="proof_doc_mime_type")
	private String proofDocMimeType;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="updated_ts")
	private Date updatedTs;

	@Column(name="updated_user")
	private String updatedUser;

	


	public byte getProofDocTypeId() {
		return this.proofDocTypeId;
	}

	public void setProofDocTypeId(byte proofDocTypeId) {
		this.proofDocTypeId = proofDocTypeId;
	}

	public Date getCreatedTs() {
		return this.createdTs;
	}

	public void setCreatedTs(Date createdTs) {
		this.createdTs = createdTs;
	}

	public String getCreatedUser() {
		return this.createdUser;
	}

	public void setCreatedUser(String createdUser) {
		this.createdUser = createdUser;
	}

	public String getIsActive() {
		return this.isActive;
	}

	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}

	public int getMaxSizeAllowedInMb() {
		return this.maxSizeAllowedInMb;
	}

	public void setMaxSizeAllowedInMb(int maxSizeAllowedInMb) {
		this.maxSizeAllowedInMb = maxSizeAllowedInMb;
	}

	public String getProofDocMimeType() {
		return this.proofDocMimeType;
	}

	public void setProofDocMimeType(String proofDocMimeType) {
		this.proofDocMimeType = proofDocMimeType;
	}

	public Date getUpdatedTs() {
		return this.updatedTs;
	}

	public void setUpdatedTs(Date updatedTs) {
		this.updatedTs = updatedTs;
	}

	public String getUpdatedUser() {
		return this.updatedUser;
	}

	public void setUpdatedUser(String updatedUser) {
		this.updatedUser = updatedUser;
	}
	
	

}
