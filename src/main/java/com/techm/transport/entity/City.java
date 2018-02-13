package com.techm.transport.entity;

import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonPropertyOrder;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;


@JsonPropertyOrder({"id", "name"})
public class City {
	
	@JsonProperty("id")
	private Integer id;
	
	@JsonProperty("name")
	private String name;
	
	@JsonIgnore
	private Integer orgId;
	
	@JsonInclude(Include.NON_NULL)
	private List<Location> locs;
	
	public City(Integer id, String name, Integer orgId) {
		this.id = id;
		this.name = name;
		this.orgId = orgId;
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

	@JsonIgnore
	public Integer getOrgId() {
		return orgId;
	}
	
	@JsonIgnore
	public void setOrgId(Integer orgId) {
		this.orgId = orgId;
	}
	
	public List<Location> getLocs() {
		return locs;
	}
	public void setLocs(List<Location> locs) {
		this.locs = locs;
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
