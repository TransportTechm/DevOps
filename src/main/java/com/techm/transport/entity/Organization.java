package com.techm.transport.entity;
import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonPropertyOrder;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;


//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.Table;

//@Entity
//@Table(name="tbl_organization")
@JsonPropertyOrder({"id", "name", "cities"})
public class Organization{
	
//	@Id
//	@GeneratedValue(strategy=GenerationType.AUTO)
//	@Column(name = "id", updatable = false, nullable = false)
	@JsonProperty("id")
	private Integer id;
	
//	@Column(name="name")
	@JsonProperty("orgName")
	private String	orgName;
	
//	@Column(name="created_at")
//	private Date createdAt;
	
//	@Column(name="created_by")
//	private String	createdBy;
	
//	@Column(name="updated_at")
//	private Date	updatedAt;
	
//	@Column(name="updated_by")
//	private String	updatedBy;
	
	@JsonInclude(Include.NON_NULL)
	private List<City> cities;
	
	public Organization(Integer id, String orgName) {
		this.id = id;
		this.orgName = orgName;
	}
	public Organization(Integer id, String orgName, List<City> cities) {
		this.id = id;
		this.orgName = orgName;
		this.cities = cities;
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	/*public Date getCreatedAt() {
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
	}*/
	
	public List<City> getCities() {
		return cities;
	}
	public void setCities(List<City> cities) {
		this.cities = cities;
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
