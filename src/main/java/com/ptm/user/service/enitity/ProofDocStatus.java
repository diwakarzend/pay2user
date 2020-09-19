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
@Table(name="proof_doc_status")
public class ProofDocStatus {
	

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="proof_doc_status_id")
	private byte proofDocStatusId;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="created_ts")
	private Date createdTs;

	@Column(name="created_user")
	private String createdUser;

	@Column(name="is_active")
	private String isActive;

	@Column(name="proof_doc_status_code")
	private String proofDocStatusCode;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="updated_ts")
	private Date updatedTs;

	@Column(name="updated_user")
	private String updatedUser;
	
	public byte getProofDocStatusId() {
		return this.proofDocStatusId;
	}

	public void setProofDocStatusId(byte proofDocStatusId) {
		this.proofDocStatusId = proofDocStatusId;
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

	public String getProofDocStatusCode() {
		return this.proofDocStatusCode;
	}

	public void setProofDocStatusCode(String proofDocStatusCode) {
		this.proofDocStatusCode = proofDocStatusCode;
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
