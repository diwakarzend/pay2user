package com.ptm.user.service.enitity;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="user_kyc_dtl")
public class UserKycDtl extends AbstractAuditingEntity {
	
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="kyc_dtl_id")
	private int kycDtlId;
	
	@ManyToOne(fetch = FetchType.LAZY,cascade = {CascadeType.ALL})
	@JoinColumn(name = "user_id", referencedColumnName = "id")
	private User user;
	
	@Column(name = "file_name")
	private String fileName;
	
	@Column(name = "file_path")
	private String filePath;
	
	@Column(name="order_seq")
	private byte orderSeq;
	
	@Column(name="doc_uuid")
	private byte docUuid;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="proof_doc_name_id")
	private ProofDocName proofDocName;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="proof_doc_type_id")
	private ProofDocType proofDocType;
	
	@Temporal(TemporalType.DATE)
	@Column(name="doc_expiry_date")
	private Date docExpiryDate;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="doc_status_id")
	private ProofDocStatus proofDocStatus;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="doc_upload_date")
	private Date docUploadDate;
	
	@Column(name="is_active")
	private String isActive;

	@Column(name="is_deleted")
	private String isDeleted;

	@Column(name="daleted_user")
	private String daletedUser;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="deleted_date")
	private Date deletedDate;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="admin_action_date")
	private Date adminActionDate;

	@Column(name="admin_action_user")
	private String adminActionUser;

	
	public int getKycDtlId() {
		return kycDtlId;
	}

	public void setKycDtlId(int kycDtlId) {
		this.kycDtlId = kycDtlId;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public ProofDocName getProofDocName() {
		return proofDocName;
	}

	public void setProofDocName(ProofDocName proofDocName) {
		this.proofDocName = proofDocName;
	}

	public ProofDocType getProofDocType() {
		return proofDocType;
	}

	public void setProofDocType(ProofDocType proofDocType) {
		this.proofDocType = proofDocType;
	}

	public Date getDocExpiryDate() {
		return docExpiryDate;
	}

	public void setDocExpiryDate(Date docExpiryDate) {
		this.docExpiryDate = docExpiryDate;
	}

	public ProofDocStatus getProofDocStatus() {
		return proofDocStatus;
	}

	public void setProofDocStatus(ProofDocStatus proofDocStatus) {
		this.proofDocStatus = proofDocStatus;
	}

	public Date getDocUploadDate() {
		return docUploadDate;
	}

	public void setDocUploadDate(Date docUploadDate) {
		this.docUploadDate = docUploadDate;
	}

	public String getIsActive() {
		return isActive;
	}

	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}

	public String getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(String isDeleted) {
		this.isDeleted = isDeleted;
	}

	public String getDaletedUser() {
		return daletedUser;
	}

	public void setDaletedUser(String daletedUser) {
		this.daletedUser = daletedUser;
	}

	public Date getDeletedDate() {
		return deletedDate;
	}

	public void setDeletedDate(Date deletedDate) {
		this.deletedDate = deletedDate;
	}

	public Date getAdminActionDate() {
		return adminActionDate;
	}

	public void setAdminActionDate(Date adminActionDate) {
		this.adminActionDate = adminActionDate;
	}

	public String getAdminActionUser() {
		return adminActionUser;
	}

	public void setAdminActionUser(String adminActionUser) {
		this.adminActionUser = adminActionUser;
	}

	public byte getOrderSeq() {
		return orderSeq;
	}

	public void setOrderSeq(byte orderSeq) {
		this.orderSeq = orderSeq;
	}

	public byte getDocUuid() {
		return docUuid;
	}

	public void setDocUuid(byte docUuid) {
		this.docUuid = docUuid;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	
	

}
