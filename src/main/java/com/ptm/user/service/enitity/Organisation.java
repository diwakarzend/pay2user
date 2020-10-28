package com.ptm.user.service.enitity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "organisation")
public class Organisation extends AbstractAuditingEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "uuid")
	private String uuid;
	
	@Column(name = "company_name")
	private String companyName;
	
	@Column(name = "company_address")
	private String companyAddress;
	
	@Column(name = "activeStatus")
	private String activeStatus;
	
	@Column(name = "brand_name")
	private String brandName;
	
	@Column(name = "is_deleted")
	private Byte isDeleted;
	
	@Column(name = "is_published")
	private Byte isPublished;
	
	@Column(name = "phone_number")
	private String phoneNumber;
	
	@Column(name = "client_finance_email")
	private String clientFinanceEmail;
	
	@Column(name = "company_type")
	private String companyType;
	
	@Column(name = "city_id")
	private String cityId;
	
	@Column(name = "state_id")
	private String stateId;
	
	@Column(name = "pin_code")
	private String pinCode;
	
	@Column(name = "remarks")
	private String remarks;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getCompanyAddress() {
		return companyAddress;
	}

	public void setCompanyAddress(String companyAddress) {
		this.companyAddress = companyAddress;
	}

	public String getActiveStatus() {
		return activeStatus;
	}

	public void setActiveStatus(String activeStatus) {
		this.activeStatus = activeStatus;
	}

	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getClientFinanceEmail() {
		return clientFinanceEmail;
	}

	public void setClientFinanceEmail(String clientFinanceEmail) {
		this.clientFinanceEmail = clientFinanceEmail;
	}

	public String getCompanyType() {
		return companyType;
	}

	public void setCompanyType(String companyType) {
		this.companyType = companyType;
	}

	public String getCityId() {
		return cityId;
	}

	public void setCityId(String cityId) {
		this.cityId = cityId;
	}

	public String getStateId() {
		return stateId;
	}

	public void setStateId(String stateId) {
		this.stateId = stateId;
	}

	public String getPinCode() {
		return pinCode;
	}

	public void setPinCode(String pinCode) {
		this.pinCode = pinCode;
	}

	public Byte getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(Byte isDeleted) {
		this.isDeleted = isDeleted;
	}

	public Byte getIsPublished() {
		return isPublished;
	}

	public void setIsPublished(Byte isPublished) {
		this.isPublished = isPublished;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	
}
