package com.techm.transport.entity;
import java.util.Date;

//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.Table;

//@Entity
//@Table(name="tbl_organization")
public class Organization{
	
//	@Id
//	@GeneratedValue(strategy=GenerationType.AUTO)
//	@Column(name = "id", updatable = false, nullable = false)
	private long id;
	
//	@Column(name="name")
	private String	orgName;
	
//	@Column(name="created_at")
	private Date createdAt;
	
//	@Column(name="created_by")
	private String	createdBy;
	
//	@Column(name="updated_at")
	private Date	updatedAt;
	
//	@Column(name="updated_by")
	private String	updatedBy;
	
	public Organization(long id, String orgName) {
		this.orgName = orgName;
	}
	public Organization(long id, String orgName, Date createdAt, String createdBy) {
		this.id = id;
		this.orgName = orgName;
		this.createdAt = createdAt;
		this.createdBy = createdBy;
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Organization other = (Organization) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
}
