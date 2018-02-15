package com.techm.transport.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class JourneyType {
	
	private Integer id;
	private String name;
	
	@JsonIgnore
	private Integer locId;
	
	public JourneyType(Integer id, String name, Integer locId) {
		this.id = id;
		this.name = name;
		this.locId = locId;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public Integer getLocId() {
		return locId;
	}
	public void setLocId(Integer locId) {
		this.locId = locId;
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
		JourneyType other = (JourneyType) obj;
		if (id != other.id)
			return false;
		return true;
	}
}
