package com.ptm.user.service.enitity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * The persistent class for the proof_doc_name database table.
 * 
 */
@Entity
@Table(name="proof_doc_name")
public class ProofDocName implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="proof_doc_name_id")
	private int proofDocNameId;

	@Column(name="acct_cat_id")
	private byte acctCatId;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="created_ts")
	private Date createdTs;

	@Column(name="created_user")
	private String createdUser;

	@Column(name="is_active")
	private String isActive;

	@Column(name="is_default")
	private String isDefault;

	@Column(name="is_extra")
	private String isExtra;


	@Column(name="proof_doc_name_en")
	private String proofDocNameEn;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="updated_ts")
	private Date updatedTs;

	@Column(name="updated_user")
	private String updatedUser;
	
	

	public ProofDocName() {
	}

	public int getProofDocNameId() {
		return this.proofDocNameId;
	}

	public void setProofDocNameId(int proofDocNameId) {
		this.proofDocNameId = proofDocNameId;
	}

	public byte getAcctCatId() {
		return this.acctCatId;
	}

	public void setAcctCatId(byte acctCatId) {
		this.acctCatId = acctCatId;
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

	public String getIsDefault() {
		return this.isDefault;
	}

	public void setIsDefault(String isDefault) {
		this.isDefault = isDefault;
	}

	public String getIsExtra() {
		return this.isExtra;
	}

	public void setIsExtra(String isExtra) {
		this.isExtra = isExtra;
	}

	

	public String getProofDocNameEn() {
		return this.proofDocNameEn;
	}

	public void setProofDocNameEn(String proofDocNameEn) {
		this.proofDocNameEn = proofDocNameEn;
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
