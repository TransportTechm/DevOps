package com.techm.transport.entity;

import java.util.Date;

public class City {
	
	private long id;
	private String name;
	private long orgId;
	
	public City(long id, String name, long orgId) {
		this.id = id;
	}
	public City(long id, String name, long orgId, Date createdAt, String createdBy) {
		this.id = id;
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public long getOrgId() {
		return orgId;
	}
	public void setOrgId(long orgId) {
		this.orgId = orgId;
	}
	
	
	@Override
    public int hashCode() 
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + (int) (id ^ (id >>> 32));
        result = prime * result + (int) (orgId ^ (orgId >>> 32));
        return result;
    }

    @Override
    public boolean equals(Object obj) 
    {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        City other = (City) obj;
        if (id != other.id && orgId != other.orgId)
            return false;
        return true;
    }
	

}
